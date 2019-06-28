using System;
using UnityEngine;

namespace data
{
	/// <summary>
	/// Data model class for the records array in the json response from the backend
	/// </summary>
	public class DataModel
	{
		/// <summary>
		/// Enable access to the DataModel's attributes using Javascript's bracket notation
		/// </summary>
		/// <example> 
		/// DataModel.summary
		/// DataModel["summary"]}
		/// </example>
		public object this[string propertyName]
		{
			get { return this.GetType().GetProperty(propertyName).GetValue(this, null); }
			set { this.GetType().GetProperty(propertyName).SetValue(this, value, null); }
		 }


		// Attributes that match those found in the csv
		public int jira_id { get; set; }
		public string summary { get; set; }
		public int issuetype_id { get; set; }
		public string issuetype_description { get; set; }
		public string issuetype_name { get; set; }
		public int issuetype_subtask { get; set; }
		public string assignee_name { get; set; }
		public int id { get; set; }

		/// <summary>
		/// A method to convert the class into a string
		/// </summary>
		override public string ToString()
		{
			string output = "";
			foreach (System.Reflection.PropertyInfo prop in this.GetType().GetProperties())
				output += prop.Name + ": " + prop.GetValue(this, null) + "\n";

			return output;
		}
	}
}