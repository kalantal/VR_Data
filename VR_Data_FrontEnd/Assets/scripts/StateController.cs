using System.Collections;
using System.Collections.Generic;
using System.Text;
using data;
using Newtonsoft.Json;
using UnityEngine;
using UnityEngine.UI;

/// <summary>
/// A controller to manage the creation of data nodes and communicate with the backend
/// </summary>
public class StateController : MonoBehaviour
{
    private Button[] buttons;
    private LineRenderer line;
    private Color lineColor;
    private Transform lineEnd;
    private Transform lineStart;
    private List<GameObject> nodes;
    private Button previousButton;
    private string url;

	/// <summary>
	/// Initialize variables
	/// </summary>
	private void Start()
    {
        lineColor = new Color(1, 1, 1, 0.5f);
        url = "http://localhost:8010/jira/queries";
        nodes = new List<GameObject>();
    }

	/// <summary>
	/// Create an HTTP request to send queries to the backend
	/// @param query a formatted query with the user filters to send to the backend
	/// @param field the field the user wants to cluster by
	/// @return an IEnumerator for the coroutine
	/// </summary>
	private IEnumerator Query(string query, string field)
    {
        // format POST body and header
        var form = new WWWForm();
        var data = Encoding.UTF8.GetBytes(query);
        var headers = form.headers;
        headers["Content-Type"] = "application/json";

        // Sent the request and wait for a response
        var www = new WWW(url, data, headers);
        yield return www;

        if (www.error == null)
        {
            // Spawn nodes in the scene if a response has been received
            Debug.Log("Success");
            SpawnNode(JsonConvert.DeserializeObject<ResponseModel>(www.text), field);
            // Draw the lines between the nodes
            DrawLines();
        }
        else
        {
            Debug.Log("WWW Error: " + www.error);
        }
    }

	/// <summary>
	/// A method for formatting, placing, and creating the node GameObjects
	/// @param response the response form the backend
	/// @param field the field the user wants to cluster by
	/// @see ResponseModel
	/// </summary>
	private void SpawnNode(ResponseModel response, string field)
    {
        // Clear the data from the previous query
        foreach (var go in nodes) Destroy(go);

        nodes.Clear();

        // Create and instantiate new nodes for each datapoint within the response
        var colorMapping = new Dictionary<string, Color>();
        for (var i = 0; i < response.records.Count; i++)
        {
            // Render the nodes
            var go = Instantiate(Resources.Load("prefabs/Node"),
                new Vector3(Random.Range(-15f, 20f), Random.Range(1f, 7f), Random.Range(-5f, 25f)),
                Quaternion.identity
            ) as GameObject;


            // Name the nodes
            go.name = "ID " + response.records[i].id;

            // Intialize the data object in the NodeController
            go.GetComponent<NodeController>().data = response.records[i];

            // Cache the node for later destruction
            nodes.Add(go);

            // Color the nodes based off an attribute
            var value = response.records[i][field].ToString();
            if (colorMapping.ContainsKey(value))
            {
                go.GetComponent<Renderer>().material.color = colorMapping[value];
            }

            else
            {
                var color = new Color(Random.Range(0, 1f), Random.Range(0, 1f), Random.Range(0, 1f));
                go.GetComponent<Renderer>().material.color = color;
                colorMapping.Add(value, color);
            }
        }
    }

	/// <summary>
	/// Creates links between the data objects
	/// </summary>
	public void DrawLines()
    {
        for (var x = 1; x < nodes.Count; x++)
        {
            // Create and configure the LineRenderer
            var line = nodes[x].AddComponent<LineRenderer>();
            line.SetColors(lineColor, lineColor);
            line.material = new Material(Shader.Find("Particles/Additive"));
            line.startWidth = 0.01f;
            line.endWidth = 0.01f;

            // Postion the line renderer based off the coordinates of 2 nodes
            line.SetPositions(new[] {nodes[x].transform.position, nodes[x - 1].transform.position});
        }
    }

	/// <summary>
	/// An EventListener that sends the query the user selects
	/// @params button the button the user selected
	/// </summary>
	/// <remarks>
	/// This is a temporary solution until the frontend can dynamically generate queries to the backend
	/// </remarks>
	public void RunSelectedQuery(Button button)
    {
        // Get all the buttons in the menu
        if (buttons == null) buttons = GameObject.FindWithTag("Menu").GetComponentsInChildren<Button>();

        var query = "";
        var field = "";

        // Prep the queries
        if (buttons[0] == button)
        {
            query =
                @"{'queries': [{'field': 'issuetype_name', 'value': 'Bug', 'occurance': 'must'}], 'pagination_token':0}";
            field = "issuetype_name";
        }
        else if (buttons[1] == button)
        {
            query =
                @"{'queries': [{'field': 'assignee_name', 'value': 'Richard', 'occurance': 'must'}], 'pagination_token':0}";
            field = "assignee_name";
        }
        else
        {
            query =
                @"{'queries': [{'occurance': 'should', 'nested_query': [{'occurance': 'must', 'field': 'assignee_name', 
					'value': 'Debbie'},{'occurance': 'must', 'field': 'assignee_name', 'value': 'Richard'}] }], 'pagination_token':0}";
            field = "assignee_name";
        }

        // Start a thread to send an HTTP request and render the nodes from the response
        StartCoroutine(Query(query, field));
    }
}