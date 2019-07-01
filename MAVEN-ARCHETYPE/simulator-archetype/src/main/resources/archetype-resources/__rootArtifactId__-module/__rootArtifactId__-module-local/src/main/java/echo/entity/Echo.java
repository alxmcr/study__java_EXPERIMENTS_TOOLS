#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.echo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Echo {
    @Id
    private String message;
    private Timestamp creationTimestamp;

    public Echo() { }

    public Echo(String message, Timestamp creationTimestamp) {
        this.message = message;
        this.creationTimestamp = creationTimestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Echo[");
        sb.append("message=").append(message);
        sb.append(",creationTimestamp=").append(creationTimestamp);
        sb.append("]");
        return sb.toString();
    }
}
