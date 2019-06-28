﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;
using NiceJson;
using UnityEngine.Networking;
using UnityEngine.SocialPlatforms;
using Random = UnityEngine.Random;
using data;

public class StateController : MonoBehaviour
{
	public List<GameObject> nodes;
	private string url = "http://localhost:8010/mockdata";

	// Use this for initialization
	void Start ()
	{		
		nodes = new List<GameObject>();
		StartCoroutine(GetRequest());
	}

	IEnumerator GetRequest()
	{
		WWW www = new WWW(url);
		yield return www;

		if (www.isDone)
		{
			List<DataModel> dataPoints = GetDataPoints(www.text);
			SpawnNode(dataPoints);
		}
	}

	private List<DataModel> GetDataPoints(string json)
	{
		// Convert response into NiceJson
		JsonObject response = (JsonObject) JsonNode.ParseJsonString(json);
		JsonArray rawDataPoints = new JsonArray();
		List<DataModel> dataPoints = new List<DataModel>();

		// Find the data array from the response
		foreach (var prop in response.Values)
		{
			if (prop is JsonArray)
			{
				rawDataPoints = (JsonArray) prop;
				break;
			}
		}

		// Convert to data model
		foreach (var data in rawDataPoints)
		{
			dataPoints.Add((DataModel) data);
		}

		return dataPoints;

	}
	
	private void SpawnNode(List<DataModel> dataPoints)
	{
		for (int i = 0; i < dataPoints.Count; i++)
		{
			GameObject node = (GameObject) Instantiate(
				Resources.Load("prefabs/DataNode"),
				new Vector3(i/2, 1, Random.Range(-5f, 5f)),
				Quaternion.identity
			);
			
			// add data
			node.GetComponent<NodeController>().data = dataPoints[i];
			nodes.Add(node);
			//node.GetComponent(NodeController); 
		}
	}

	// include method to Instantiate instances of prefabs for each node
	
	// Update is called once per frame
	void Update () {
		
	}
}
