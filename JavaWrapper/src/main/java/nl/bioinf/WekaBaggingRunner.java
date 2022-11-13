package nl.bioinf;

import weka.classifiers.meta.Bagging;
import weka.core.Instances;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WekaBaggingRunner {

    public static void main(String[] args) {
        CLHandler arguments = new CLHandler(args);
        WekaBaggingRunner runner = new WekaBaggingRunner();
        if (args.length == 0){
            System.err.println("There are no arguments given, please select the desired arff file.");
            arguments.formatter.printHelp("WekaRunner",arguments.options, true);
        }
        if (arguments.cmd.getOptionValue("infile") != null) {
            try {
                Instances newData = runner.loadArff(arguments.cmd.getOptionValue("infile"));
                Instances result = runner.runModel(newData);
                runner.writeOutput(arguments.cmd.getOptionValue("output"), result, arguments.extension);
            } catch (Exception e) {
                System.err.println("Some error occurred");
                e.printStackTrace();
            }
        }
    }

    /**
     * @param unknownData Instances who will be classified
     * @return results of classification
     */
    private Instances runModel(Instances unknownData) {
        try {
            Bagging model = loadClassifier();
            return classifyNewInstance(model, unknownData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param dataFile load in Arff file and convert to instances
     * @return instances
     */
    private Instances loadArff(String dataFile) throws IOException {
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(dataFile);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            throw new IOException("could not read from file");
        }
    }

    /**
     * read the Weka model from file path
     * @return finalized Weka model from bagging model
     */
    private Bagging loadClassifier() throws Exception {
        String modelPath = "src/main/resources/bagging2.model";
        return (Bagging) weka.core.SerializationHelper.read(modelPath);
    }

    /**
     * @param bagging model used algorithm for unseen data
     * @param newData unclassified data in an instances object
     * @return classified instances in arff format
     */
    private Instances classifyNewInstance(Bagging bagging, Instances newData) throws Exception {
        // create copy
        Instances labeled = new Instances(newData);
        // label instances
        for (int i = 0; i < newData.numInstances(); i++) {
            double clsLabel = bagging.classifyInstance(newData.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }
        return labeled;
    }


    /**
     * @param fileName full path from output file
     * @param res Instances object of the results in arff format
     * @param extension the desired output extension from the user input
     * prints result to commandline if the user didn't give an output file
     * otherwise checks output extension and write the result in the desired
     * output file
     */
    private void writeOutput(String fileName, Instances res, String extension) throws IOException {
        if (extension == null){
            for (int x = 0; x < res.numInstances(); x++){
                boolean Stroke = res.instance(x).classValue() != 0;
                System.out.println("Patient: " + x + " has a raised chance of stroke based on the model: " + Stroke);
            }
        }

        else if (extension.equals("csv")) {
            CSVSaver saver = new CSVSaver();
            saver.setInstances(res);
            saver.setFile(new File(fileName));
            saver.writeBatch();
        }

        else if (extension.equals("arff")){
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(String.valueOf(res));
            writer.newLine();
            writer.close();

        }
    }
}