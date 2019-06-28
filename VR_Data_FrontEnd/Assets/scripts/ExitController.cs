﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// An exit controller
/// </summary>
public class ExitController : MonoBehaviour {
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.Escape)) {
			Application.Quit ();
		}
	}
}
