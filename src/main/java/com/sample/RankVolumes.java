package com.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.model.Volume;

/**
 * Example application loading random Volumes in the working memory.
 * The rules determine and assign a rank to the Volumes. The example would give a notion of externalizing rules.
 * With the same of facts the application can show a different outcome depending on the rules applied. It also
 * demonstrates how the sorting algorithm is different in 3GL coding environment.
 */
public class RankVolumes {

    public static final void main(String[] args) {
        KieRuntimeLogger klogger = null;
        try {
            
            // Get some random values.
            Set<Volume> volumes = getRandomVolumes();
            
            // Initialize default knowledge base.
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = getDefaultSession(kContainer);
            klogger = ks.getLoggers().newFileLogger(kSession, "target/thebiggeris");
            
            // Default ranking of volumes.
            System.out.println("The ranking is:");
            rankVolumes(volumes, kSession);
            
            // Get an alternative ranking of volumes.
            System.out.println("");
            System.out.println("Let's change the rules, the ranking is:");
            kSession = getAlternativeSession(kContainer);
            klogger = ks.getLoggers().newFileLogger(kSession, "target/theloweris");
            rankVolumes(volumes, kSession);

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            klogger.close();
        }
    }

    /**
     * @param kContainer default KIE Container.
     * @return
     */
    private static KieSession getDefaultSession(KieContainer kContainer) {
        KieBase kBase1 = kContainer.getKieBase();
        KieSession kSession = kBase1.newKieSession();
        return kSession;
    }

    /**
     * @param kContainer alternative KIE Container.
     * @return
     */
    private static KieSession getAlternativeSession(KieContainer kContainer) {
        KieBase kBase1 = kContainer.getKieBase("example-scenario2");
        KieSession kSession = kBase1.newKieSession();
        return kSession;
    }

    /**
     * @param volumes random volumes, i'm sure.
     * @param kSession the knowledge session.
     */
    private static void rankVolumes(Set<Volume> volumes, KieSession kSession) {
        // Insert facts.
        for (Volume v : volumes) {
            kSession.insert(v);
        }
        
        // Go!
        kSession.fireAllRules();
        
        // Sort volumes by rank. 
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<Volume> objects = new ArrayList(kSession.getObjects());
        Collections.sort(objects, (o1, o2) -> o1.getRank() - o2.getRank());
        
        // Output to console.
        objects.forEach(o -> System.out.println(o));
    }

    /**
     * @return random volumes.
     */
    private static Set<Volume> getRandomVolumes() {
        Random r = new Random();
        Set<Volume> volumes = new HashSet<Volume>();
        for (int i = 0; i < 4; i++) {
            volumes.add(new Volume(r.nextInt(100)));
        }
        return volumes;
    }
}
