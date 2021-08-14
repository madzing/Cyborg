package Gui;

import java.util.HashSet;
import java.util.Set;

public abstract class GuiReder {
	
	private Set<GuiListener> _alleBeobachter;

    /**
     * Initialisiert ein beobachtbares Subwerkzeug.
     */
    public GuiReder()
    {
        _alleBeobachter = new HashSet<GuiListener>();
    }
    /**
     * Registriert einen Beobachter an diesem Subwerkzeug. Der Beobachter wird
     * informiert, wenn sich bei diesem Werkzeug etwas ändert.
     * 
     * @require beobachter != null
     */
    public void registriereBeobachter(GuiListener beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _alleBeobachter.add(beobachter);
    }

    /**
     * Entfernt einen Beobachter dieses Subwerkzeugs. Wenn der Beobachter gar
     * nicht registriert war, passiert nichts.
     */
    public void entferneBeobachter(GuiListener beobachter)
    {
        _alleBeobachter.remove(beobachter);
    }

    /**
     * Informiert alle an diesem Subwerkzeug registrierten Beobachter über eine
     * Änderung.
     * 
     * Diese Methode muss von der erbenden Klasse immer dann aufgerufen werden,
     * wenn eine Änderung geschehen ist, die für Beobachter interessant ist.
     */
    protected void informiereUeberAenderung()
    {
        for (GuiListener beobachter : _alleBeobachter)
        {
            beobachter.reagiereAufAenderung();
        }
    }
}