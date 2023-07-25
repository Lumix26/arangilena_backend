package version1.demo.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrello {
    private Long id_acquirente;
    private HashMap<Long,Integer> dettaglioProdotti;

    public Carrello() {
        dettaglioProdotti = new HashMap<>();
    }

    public void aggiungiProdotto(Entry<Long,Integer> entry){
        if (!dettaglioProdotti.containsKey(entry.getKey())){
            dettaglioProdotti.put(entry.getKey(), entry.getValue());
        }else{
            int tmp = dettaglioProdotti.get(entry.getKey());
            dettaglioProdotti.put(entry.getKey(), tmp+entry.getValue());
        }
    }

    public void eliminaProdotto(Entry<Long,Integer> entry){
        if(dettaglioProdotti.containsKey(entry.getKey())){
            dettaglioProdotti.remove(entry.getKey(),entry.getValue());
        }
    }

    public void svuotaCarrello(){
        dettaglioProdotti.clear();
    }

    public HashMap<Long,Integer> getCarrello(){
        return dettaglioProdotti;
    }

    public Long getId_acquirente() {
        return id_acquirente;
    }

    public void setId_acquirente(Long id_acquirente) {
        this.id_acquirente = id_acquirente;
    }

    
    
}
