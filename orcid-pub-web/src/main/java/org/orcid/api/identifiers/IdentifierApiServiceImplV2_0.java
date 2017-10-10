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
package org.orcid.api.identifiers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.orcid.api.identifiers.delegator.IdentifierApiServiceDelegator;
import org.orcid.core.api.OrcidApiConstants;
import org.orcid.jaxb.model.message.ScopeConstants;
import org.springframework.beans.factory.annotation.Value;

import com.sun.jersey.api.provider.jaxb.XmlHeader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@Api("Identifier API v2.0")
@Path("/v2.0" + OrcidApiConstants.IDENTIFIER_PATH)
public class IdentifierApiServiceImplV2_0 {

    public final String xmllocation = "<?xml-stylesheet type=\"text/xsl\" href=\"https://orcid.org/static/css/identifierTypes.xsl\"?>";
    
    private IdentifierApiServiceDelegator serviceDelegator;
    
    public void setServiceDelegator(IdentifierApiServiceDelegator serviceDelegator) {
        this.serviceDelegator = serviceDelegator;
    }

    /**
     * @return Available external-id types in the ORCID registry
     */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("")
    @ApiOperation(value = "Fetch identifier type map.  Defaults to English descriptions", authorizations = { @Authorization(value = "orcid_two_legs", scopes = { @AuthorizationScope(scope = ScopeConstants.READ_PUBLIC, description = "you need this") }) })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "")})
    @XmlHeader(xmllocation)
    public Response viewIdentifierTypes(@ApiParam() @QueryParam("locale") String locale) {
        if (locale == null || locale.isEmpty())
            locale = "en";
        return serviceDelegator.getIdentifierTypes(locale);
    }

}
