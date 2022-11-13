package nl.bioinf;

import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;


public class CLHandler {
    CommandLine cmd;
    Options options = createOptions();
    String extension = null;
    HelpFormatter formatter = new HelpFormatter();

    /**
     * create an arguments parser which can be used for the commandline
     * @return option formatter
     */
    private Options createOptions(){
        Options options = new Options();
        options.addOption(new Option("i", "infile", true,
                "Input file with unknown instances"));
        options.addOption(new Option("o", "output", true,
                "Output file to be created, if a path is given from the user"));
        return options;
    }

    /**
     * parsing commandline arguments
     * creates help which will be printed if needed by the user
     */
    public CLHandler(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parseOutputFile();
    }

    /**
     * parsing output file extension
     * in order to decide which format to write the output
     */
    private void parseOutputFile() {
        if (this.cmd.getOptionValue("output") != null){
            String output = this.cmd.getOptionValue("output");
            String format = FilenameUtils.getExtension(output);

            if ("csv".equals(format)) {
                this.extension = "csv";
            } else {
                this.extension = "arff";
            }
        }
    }
}
