using System;
using System.Collections;
using System.Collections.Generic;
using data;
using UnityEngine;
using Random = UnityEngine.Random;

/// <summary>
/// A controller for each node to control its state
/// </summary>
public class NodeController : MonoBehaviour
{
	public DataModel data { get; set; }
	
	/// <summary>
	/// Renders the node under the Data child in the hierarchy
	/// </summary>
	void Start ()
	{
		transform.parent = GameObject.Find("Game/Data").transform;
	}

	/// <summary>
	/// A method to get the node's description from the data its been assigned
	/// @return a string description with the response data the node is in control of
	/// </summary>
	public string GetDescription()
	{
		return data.issuetype_description + " " + data.assignee_name + " " + data.issuetype_name;
	}
}
