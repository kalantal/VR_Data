using System.Collections.Generic;

namespace data
{
    /// <summary>
    /// A model class representative of the response from the backend
    /// @see DataModel
    /// </summary>
    public class ResponseModel
    {
        public List<DataModel> records { get; set; }
        public int pagination_token;
    }
}