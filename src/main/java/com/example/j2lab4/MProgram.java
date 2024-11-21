package com.example.j2lab4;
import java.util.*;
import java.util.stream.Collectors;

public class MProgram {

    ArrayList<Command>       arrayList     = new ArrayList<>();
    ArrayList<IProgObserver> allObserver   = new ArrayList<>();
    ArrayList<Integer>       addSpace      = new ArrayList<>();
    Executer                 executer;
    private int              indRunCommand = 0;

    // вспомогательняе методы

    public void               setExec(Executer exec)         { executer = exec; }

    public ArrayList<Command> copy()                         { return new ArrayList<>(arrayList);  }

    public boolean            isRunAddress(int i)            { return addSpace.contains(i); }

    // методы модели

    public void               eventCall()                    { allObserver.forEach(action->action.event(this)); }

    public void               addObserver(IProgObserver obs) { allObserver.add(obs); }


    // функциональные методы
    public void add        (Command c) { arrayList.add(c); eventCall(); }

    public void removeInstr(Command c) { arrayList.remove(c); eventCall(); }

    public void left       (Command c) { Collections.swap(arrayList, arrayList.indexOf(c), arrayList.indexOf(c)-1); eventCall(); }

    public void right      (Command c) { Collections.swap(arrayList, arrayList.indexOf(c), arrayList.indexOf(c)+1); eventCall(); }

    public void reload     (ICpu cpu)
    {
        indRunCommand = 0;
        for (Command c : arrayList) c.clrRun();
        addSpace.clear();
        cpu.eventCall();
        eventCall();
    }

    public void runStep(ICpu cpu) {
        if (indRunCommand < arrayList.size()) {
            Command command = arrayList.get(indRunCommand);
            try
            {
                executer.run(command);

                if (indRunCommand > 0) { arrayList.get(indRunCommand - 1).clrRun(); }
                command.setRun();

                indRunCommand++;

                if (command.getInstr().equals("init")) addSpace.add(Integer.parseInt(command.getArg1()));
                if (command.getInstr().equals("st"))   addSpace.add(Integer.parseInt(command.getArg2()));

                eventCall();
                cpu.eventCall();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    // методы внутреннего подсчёта

    private Map<String, Long> getCountCommands() {
        return arrayList
                .stream()
                .collect(Collectors.groupingBy(Command::getInstr, Collectors.counting()));
    }

    // задача 1
    public void mostOftCommand() {
        Map <String, Long> mapCommands             = getCountCommands();
        Comparator<Map.Entry<String, Long>> compar = Map.Entry.comparingByValue();

        System.out.println("Most Often Command is " +
                Collections
                        .max(mapCommands.entrySet(), compar)
                        .getKey()
        );
    }

    // задача 2
    public void addressRange() {
        ArrayList<Integer> address = arrayList.stream()
                .filter((command) ->
                        switch (command.str[0]) {
                            case "ld", "st", "init" -> true;
                            default -> false;
                        })
                .map((command) -> {
                    int numOfStr = switch (command.str[0]) {
                        case "ld", "st" -> 2;
                        default -> 1;
                    };
                    return Integer.parseInt(command.str[numOfStr]);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Address range is from " +
                Collections.min(address) + " to " + Collections.max(address)
        );
    }

    // задача 3
    public Map<String, Long> commandList() {
        System.out.println("Command List: ");

        Map<String, Long> mapCommands = getCountCommands();
        Comparator<Map.Entry<String, Long>> compar = Map.Entry.comparingByValue(Comparator.reverseOrder());

        return mapCommands
                .entrySet()
                .stream()
                .sorted(compar)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> y, LinkedHashMap::new
                ));

    }
}
