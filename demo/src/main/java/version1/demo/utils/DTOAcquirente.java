package version1.demo.utils;

import version1.demo.models.utente.Indirizzo;
import version1.demo.models.utente.Recapito;

public class DTOAcquirente {
    private String username;
    private String password;
    private Indirizzo indirizzo;
    private Recapito recapiti;
    private String piva;
    private String ragioneSociale;
    

    public DTOAcquirente() {
    }
    


    /* Getter & Setter */

    public DTOAcquirente(String username, String password, Indirizzo indirizzo, Recapito recapiti, String piva,
            String ragioneSociale) {
        this.username = username;
        this.password = password;
        this.indirizzo = indirizzo;
        this.recapiti = recapiti;
        this.piva = piva;
        this.ragioneSociale = ragioneSociale;
    }



    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Indirizzo getIndirizzo() {
        return indirizzo;
    }


    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }


    public Recapito getRecapiti() {
        return recapiti;
    }


    public void setRecapiti(Recapito recapiti) {
        this.recapiti = recapiti;
    }


    public String getPiva() {
        return piva;
    }


    public void setPiva(String piva) {
        this.piva = piva;
    }


    @Override
    public String toString() {
        return "DTOAcquirente [username=" + username + ", password=" + password + ", indirizzo=" + indirizzo
                + ", recapiti=" + recapiti + ", piva=" + piva + ", ragioneSociale=" + ragioneSociale + "]";
    }



    public String getRagioneSociale() {
        return ragioneSociale;
    }


    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
        result = prime * result + ((recapiti == null) ? 0 : recapiti.hashCode());
        result = prime * result + ((piva == null) ? 0 : piva.hashCode());
        result = prime * result + ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DTOAcquirente other = (DTOAcquirente) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (indirizzo == null) {
            if (other.indirizzo != null)
                return false;
        } else if (!indirizzo.equals(other.indirizzo))
            return false;
        if (recapiti == null) {
            if (other.recapiti != null)
                return false;
        } else if (!recapiti.equals(other.recapiti))
            return false;
        if (piva == null) {
            if (other.piva != null)
                return false;
        } else if (!piva.equals(other.piva))
            return false;
        if (ragioneSociale == null) {
            if (other.ragioneSociale != null)
                return false;
        } else if (!ragioneSociale.equals(other.ragioneSociale))
            return false;
        return true;
    }

    
    
}
