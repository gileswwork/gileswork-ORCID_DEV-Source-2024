/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.core.manager;

import org.orcid.core.manager.read_only.AffiliationsManagerReadOnly;
import org.orcid.jaxb.model.common_v2.Visibility;
import org.orcid.jaxb.model.record_v2.Education;
import org.orcid.jaxb.model.record_v2.Employment;

public interface AffiliationsManager extends AffiliationsManagerReadOnly {
    /**
     * Add a new education to the given user
     * @param orcid
     *          The user to add the education
     * @param education
     *          The education to add
     * @return the added education
     * */
    Education createEducationAffiliation(String orcid, Education education, boolean isApiRequest);
    
    /**
     * Updates a education that belongs to the given user
     * @param orcid
     *          The user
     * @param education
     *          The education to update
     * @return the updated education
     * */
    Education updateEducationAffiliation(String orcid, Education education, boolean isApiRequest);
        
    /**
     * Add a new employment to the given user
     * @param orcid
     *          The user to add the employment
     * @param employment
     *          The employment to add
     * @return the added employment
     * */
    Employment createEmploymentAffiliation(String orcid, Employment employment, boolean isApiRequest);
    
    /**
     * Updates a employment that belongs to the given user
     * @param orcid
     *          The user
     * @param employment
     *          The employment to update
     * @return the updated employment
     * */
    Employment updateEmploymentAffiliation(String orcid, Employment employment, boolean isApiRequest);
    
    /**
     * Deletes a given affiliation, if and only if, the client that requested the delete is the source of the affiliation
     * @param orcid
     *          the affiliation owner
     * @param affiliationId
     *          The affiliation id                 
     * @return true if the affiliation was deleted, false otherwise
     * */
    boolean checkSourceAndDelete(String orcid, Long affiliationId);        
    
    boolean updateVisibility(String orcid, Long affiliationId, Visibility visibility);        
}
