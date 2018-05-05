/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.GuildMember;
import guildmembers.GuildMaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Bálint
 */
public class RodgortFactory {

    private static int readAmountOfWorkersFromConfigFile() {

        File config = new File("src/resources/config.txt");
        System.out.format("Cofig file read from: %s\n", config.getPath());
        int ret = 0;
        try {
            Scanner sc = new Scanner(config);
            ret = sc.nextInt();
        } catch (FileNotFoundException ex) {
            System.err.println("Config file cannot be found!\n[" + ex.getMessage() + ']');
        }
        return ret;
    }
    
    private static CraftPhase chooseInitPhase()
    {
        Random rnd = new Random();
        int phaseInit = rnd.nextInt(750) + 1;
        if((phaseInit % 2) == 0){
            return CraftPhase.PHASE1;
        } 
        return CraftPhase.PHASE2;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int numberOfWorkers = readAmountOfWorkersFromConfigFile();

        // Initialize guildmaster
        GuildMaster gm = GuildMaster.getInstance();
        gm.fillBank();
        // Initilize guild bank
        Random rand = new Random();
        gm.getListOfBaseMaterials().forEach((material) -> {
            int inc = rand.nextInt(20) + 1;
            gm.addToBank(material, inc);
        });
        // Run guildmaster
        Thread gmRunner = new Thread(gm);
        gmRunner.start();

        //Initilize guild members
//        GuildMember cheraFox = new GuildMember("Chera Fox", gm.bank, CraftPhase.PHASE1);
//        GuildMember feron = new GuildMember("Feron Ragemendler", gm.bank, CraftPhase.PHASE2);
//        gm.members.add(cheraFox);
//        gm.members.add(feron);
        for (int i = 0; i < numberOfWorkers; i++ ) {
             gm.members.add(new GuildMember("worker #"+i, gm.bank, chooseInitPhase()));
        }
        List<Thread> threads = new ArrayList<>();
        
        gm.members.forEach((m) -> {
            threads.add(new Thread(m));
        });

        threads.forEach((thread) -> {
            thread.start();
        });

    }
}
