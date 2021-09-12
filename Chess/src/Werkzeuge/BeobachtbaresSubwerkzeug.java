package Werkzeuge;

import java.util.HashSet;
import java.util.Set;



public abstract class BeobachtbaresSubwerkzeug {
	private Set<BeobachtendesWerkzeug> _alleBeobachter;

    /**
     * Initialisiert ein beobachtbares Subwerkzeug.
     */
    public BeobachtbaresSubwerkzeug()
    {
        _alleBeobachter = new HashSet<BeobachtendesWerkzeug>();
    }

    /**
     * Registriert einen Beobachter an diesem Subwerkzeug. Der Beobachter wird
     * informiert, wenn sich bei diesem Werkzeug etwas ändert.
     * 
     * @require beobachter != null
     */
    public void registriereBeobachter(BeobachtendesWerkzeug beobachter)
    {
        assert beobachter != null : "Vorbedingung verletzt: beobachter != null";
        _alleBeobachter.add(beobachter);
    }

    /**
     * Entfernt einen Beobachter dieses Subwerkzeugs. Wenn der Beobachter gar
     * nicht registriert war, passiert nichts.
     */
    public void entferneBeobachter(BeobachtendesWerkzeug beobachter)
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
        for (BeobachtendesWerkzeug beobachter : _alleBeobachter)
        {
            beobachter.reagiereAufAenderung();
        }
    }
}
