using NiceJson;

namespace data
{
    public class DataModel
    {
        private int num { get; set; }
        
        public static explicit operator DataModel(JsonNode node)
        {
            DataModel output = new DataModel(){ num = (int) node };
            return output;
        }
    }
}