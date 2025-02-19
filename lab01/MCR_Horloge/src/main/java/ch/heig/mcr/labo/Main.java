package ch.heig.mcr.labo;


import ch.heig.mcr.labo.GUI.GUI;


public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please put the number of chronos you'd like to have");
            return;
        }

        GUI gui = new GUI(Integer.parseInt(args[0]));
        gui.run();

    }
}