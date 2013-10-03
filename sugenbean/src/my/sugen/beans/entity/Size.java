/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.sugen.beans.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author cafestop
 */
@Entity
public class Size implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String sizeType;
    
    private String sizeEntry;

    public Size(String sizeType, String sizeEntry) {
        this.sizeType = sizeType;
        this.sizeEntry = sizeEntry;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getSizeEntry() {
        return sizeEntry;
    }

    public void setSizeEntry(String sizeEntry) {
        this.sizeEntry = sizeEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Size)) {
            return false;
        }
        Size other = (Size) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "my.sugen.beans.entity.Size[ id=" + id + " ]";
    }
    
}
