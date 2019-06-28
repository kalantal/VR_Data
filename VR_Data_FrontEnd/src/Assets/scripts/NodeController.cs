using System;
using System.Collections;
using System.Collections.Generic;
using data;
using UnityEngine;
using Random = UnityEngine.Random;

public class NodeController : MonoBehaviour
{
	public DataModel data { get; set; }
	void Start ()
	{
		// Set node to random color
		Color color = Random.ColorHSV();
		GetComponent<Renderer>().material.color = color;
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
