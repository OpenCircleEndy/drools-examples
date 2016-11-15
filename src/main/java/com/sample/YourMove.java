package com.sample;

import java.awt.Point;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.model.ChessPiece;
import com.sample.model.ChessPiece.Color;
import com.sample.model.ChessPiece.Type;

/**
 * Example application loading a chess pieces into working memory. The application will determine the possible moves for
 * the chess pieces added to the knowledge session. The game rules are externalized and expressed in Drools rules.
 */
public class YourMove {
    
    public static final void main(String[] args) {      
        KieRuntimeLogger klogger = null;
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieBase base = kContainer.getKieBase();
            KieSession kSession = base.newKieSession();
            klogger = ks.getLoggers().newFileLogger(kSession, "target/chess");

            // Place black on the board.
            for(int i=1;i<9;i++) {
                kSession.insert(new ChessPiece(Type.PAWN, Color.BLACK, new Point(i, 7)));
            }
            kSession.insert(new ChessPiece(Type.ROOK, Color.BLACK, new Point(1, 8)));
            kSession.insert(new ChessPiece(Type.KNIGHT, Color.BLACK, new Point(2, 8)));
            kSession.insert(new ChessPiece(Type.BISHOP, Color.BLACK, new Point(3, 8)));
            kSession.insert(new ChessPiece(Type.QUEEN, Color.BLACK, new Point(4, 8)));
            kSession.insert(new ChessPiece(Type.KING, Color.BLACK, new Point(5, 8)));
            kSession.insert(new ChessPiece(Type.BISHOP, Color.BLACK, new Point(6, 8)));
            kSession.insert(new ChessPiece(Type.KNIGHT, Color.BLACK, new Point(7, 8)));
            kSession.insert(new ChessPiece(Type.ROOK, Color.BLACK, new Point(8, 8)));
            kSession.fireAllRules();
            kSession.getObjects().forEach(o -> System.out.println(o));

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            klogger.close();
        }
    }
}
