package es.unileon.happycow.handler;

import java.io.Serializable;

public interface IdHandler extends Serializable {

    public int compareTo(IdHandler another);

    @Override
    public String toString();
    
    public String getValue();

    @Override
    public boolean equals(Object obj);
}
