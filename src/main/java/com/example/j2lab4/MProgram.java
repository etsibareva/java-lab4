package com.example.j2lab4;
import java.util.*;
import java.util.stream.Collectors;

public class MProgram {

    ArrayList<Command>       commandList   = new ArrayList<>();
    ArrayList<IProgObserver> allObserver   = new ArrayList<>();
    ArrayList<Integer>       addSpace      = new ArrayList<>();
    Executer                 executer;
    private int              indRunCommand = 0;
    DAO dao                                = new DAO(commandList);

    // вспомогательняе методы

    public void               setExec(Executer exec)         { executer = exec; }

    public ArrayList<Command> copy()                         { return new ArrayList<>(commandList);  }

    public boolean            isRunAddress(int i)            { return addSpace.contains(i); }

    // методы модели

    public void               eventCall()                    { allObserver.forEach(action->action.event(this)); }

    public void               addObserver(IProgObserver obs) { allObserver.add(obs); eventCall(); }


    // функциональные методы
    public void add        (Command c) { dao.add(c); eventCall();    }

    public void removeInstr(Command c) { dao.remove(c); eventCall(); }

    public void left       (Command c) { dao.left(c); eventCall();   }

    public void right      (Command c) { dao.right(c); eventCall();  }

    public void reload     (MCpu cpu) {
        indRunCommand = 0;
        for (Command c : commandList) c.clrRun();
        addSpace.clear();
        cpu.eventCall();
        eventCall();
    }

    public void runStep(MCpu cpu) {
        if (indRunCommand < commandList.size()) {
            Command command = commandList.get(indRunCommand);
            try
            {
                executer.run(command);

                if (indRunCommand > 0) { commandList.get(indRunCommand - 1).clrRun(); }
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
        return commandList
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
        ArrayList<Integer> address = commandList.stream()
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
