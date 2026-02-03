package leonparser;

import leonparser.mapper.MapperDtoToModel;
import leonparser.parser.LeonParser;
import leonparser.printer.Printer;

public class Main {
    public static void main(String[] args) {
        LeonParser parser = new LeonParser();
        Printer.printAll(MapperDtoToModel.sportDtoToModel(parser.parseAll()));
    }
}