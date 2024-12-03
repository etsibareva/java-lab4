package com.example.j2lab4;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    ArrayList<Command> arrayList;
    Connection connection;

    DAO(ArrayList<Command> arr) {
        arrayList = arr;
        connect();
        updateAll();
    }

    void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:program.db");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("ex.getMessage() 1");
        }
    }

    public void add (Command c) {
        try {
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO AllCommand(NAME, ARG1, ARG2, ORDERID) VALUES (?,?,?,?)");

            if (c.getInstr().equals("init") ||
                c.getInstr().equals("st") ||
                c.getInstr().equals("ld")
            ) {
                pst.setString(1, c.getInstr());
                pst.setString(2, c.getArg1());
                pst.setString(3, c.getArg2());
            }
            else {
                pst.setString(1, c.getInstr());
                pst.setString(2, "NULL");
                pst.setString(3, "NULL");
            }

            pst.setInt(4, arrayList.size()+1);

            pst.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("ex.getMessage() 2");
        }
        updateAll();
    }

    public void remove (Command c) {
        try {
            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM AllCommand WHERE ID = ?");
            pst.setInt(1, c.getID());
            pst.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        updateAll();
    }

    public void left (Command c) {
        try {
            int curPos = arrayList.indexOf(c)+1;
            int curID = c.getID();

            int leftPos = arrayList.indexOf(c)-1+1;
            int leftID = arrayList.get(arrayList.indexOf(c)-1).getID();

            PreparedStatement pst = connection.prepareStatement(
                    "UPDATE AllCommand SET ORDERID = ? WHERE ID = ?"
            );

            pst.setInt(1, curPos);
            pst.setInt(2, leftID);
            pst.executeUpdate();

            PreparedStatement pst1 = connection.prepareStatement(
                    "UPDATE AllCommand SET ORDERID = ? WHERE ID = ?"
            );

            pst1.setInt(1, leftPos);
            pst1.setInt(2, curID);
            pst1.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        updateAll();
    }

    public void right (Command c) {
        try {
            int curPos = arrayList.indexOf(c)+1;
            int curID = c.getID();

            int rightPos = arrayList.indexOf(c)+1+1;
            int rightID = arrayList.get(arrayList.indexOf(c)+1).getID();

            PreparedStatement pst = connection.prepareStatement(
                    "UPDATE AllCommand SET ORDERID = ? WHERE ID = ?"
            );

            pst.setInt(1, curPos);
            pst.setInt(2, rightID);
            pst.executeUpdate();

            PreparedStatement pst1 = connection.prepareStatement(
                    "UPDATE AllCommand SET ORDERID = ? WHERE ID = ?"
            );

            pst1.setInt(1, rightPos);
            pst1.setInt(2, curID);
            pst1.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        updateAll();
    }

    void updateAll() {
        arrayList.clear();
        try {
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery(
                    "SELECT * from AllCommand ORDER BY ORDERID");
            while (r.next()) {
                if (r.getString("NAME").equals("ld") ||
                    r.getString("NAME").equals("st") ||
                    r.getString("NAME").equals("init")
                   ) {
                    Command command = new Command(
                            r.getString("NAME"),
                            r.getString("ARG1"),
                            r.getString("ARG2"));

                    command.setID(r.getInt("ID"));
                    System.out.println(r.getInt("ID") + "  " + command.getID());
                    arrayList.add(command);
                }
                else {
                    Command command = new Command(
                            r.getString("NAME"));
                    command.setID(r.getInt("ID"));
                    arrayList.add(command);
                    System.out.println(r.getInt("ID") + "  " + command.getID());
                }
            }
        }catch (SQLException ex) {
            System.out.println("ex.getMessage() 3");
        }
    }

}
