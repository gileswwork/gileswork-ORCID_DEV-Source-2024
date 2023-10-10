package org.orcid.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.orcid.persistence.dao.EmailDomainDao;
import org.orcid.persistence.jpa.entities.EmailDomainEntity;
import org.orcid.persistence.jpa.entities.EmailEntity;
import org.orcid.persistence.jpa.entities.EmailDomainEntity.DomainCategory;

public class EmailDomainDaoImpl extends GenericDaoImpl<EmailDomainEntity, Long> implements EmailDomainDao {

    
    public EmailDomainDaoImpl() {
        super(EmailDomainEntity.class);
    }
    
    @Override
    public EmailDomainEntity createEmailDomain(String emailDomain, DomainCategory category) {
        EmailDomainEntity e = new EmailDomainEntity();
        e.setEmailDomain(emailDomain);
        e.setCategory(category);
        entityManager.persist(e);
        return e;
    }

    @Override
    public boolean updateCategory(long id, DomainCategory category) {
        Query query = entityManager.createNativeQuery("UPDATE email_domain SET category=:category WHERE id = :id");
        query.setParameter("id", id);        
        query.setParameter("category", category);
        return query.executeUpdate() > 0; 
    }

    @Override
    public EmailDomainEntity findByEmailDoman(String emailDomain) {
        TypedQuery<EmailDomainEntity> query = entityManager.createQuery("from EmailDomainEntity where emailDomain = :emailDomain", EmailDomainEntity.class);
        query.setParameter("emailDomain", emailDomain);
        List<EmailDomainEntity> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<EmailDomainEntity> findByCategory(DomainCategory category) {
        TypedQuery<EmailDomainEntity> query = entityManager.createQuery("from EmailDomainEntity where category = :category", EmailDomainEntity.class);
        query.setParameter("category", category);
        List<EmailDomainEntity> results = query.getResultList();
        return results.isEmpty() ? null : results;
    }

}
