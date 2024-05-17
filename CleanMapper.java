import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (isValid(line)) {
            String cleanedLine = selectColumns(line);
            context.write(new Text(""), new Text(cleanedLine));
        }
    }
    private boolean isValid(String line) {
        return true;
    }
    private String selectColumns(String line) {
        return line;
    }
}