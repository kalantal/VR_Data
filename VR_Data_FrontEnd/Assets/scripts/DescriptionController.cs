using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

/// <summary>
/// A controller for the description to manage state
/// </summary>
public class DescriptionController : MonoBehaviour
{
	private string description;
	private GameObject nodeGO;
	private Text textGO;

	
	/// <summary>
	/// Start method
	/// Initializes class variables
	/// </summary>
	void Start ()
	{
		// Get the node GameObject
		// the node is grandparent due to the canvas object
		nodeGO = transform.parent.parent.gameObject;
		
		// Get reference to the Text GameObject
		textGO = GetComponent<Text>();
		
		// Set the node's position with respect to the nodeGO
		Vector3 position = nodeGO.transform.position;
		transform.position = new Vector3(position.x, position.y - .3f, position.z);

	}
	
	/// <summary>
	/// Updates the node description and angle so that the description is always facing the user
	/// </summary>
	void Update () {
		
		if (description == null)
		{
			description = nodeGO.GetComponent<NodeController>().GetDescription();
			textGO.text = description;
		}

			transform.LookAt(2*transform.position - Camera.main.transform.position);
		

	}
}
