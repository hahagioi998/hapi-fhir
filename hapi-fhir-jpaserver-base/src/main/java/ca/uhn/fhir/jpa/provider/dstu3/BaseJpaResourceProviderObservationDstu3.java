package ca.uhn.fhir.jpa.provider.dstu3;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDaoObservation;
import ca.uhn.fhir.jpa.model.util.JpaConstants;
import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.valueset.BundleTypeEnum;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.Constants;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.*;
import org.hl7.fhir.dstu3.model.Observation;
import org.hl7.fhir.dstu3.model.UnsignedIntType;
import org.hl7.fhir.instance.model.api.IPrimitiveType;

/*
 * #%L
 * HAPI FHIR JPA Server
 * %%
 * Copyright (C) 2014 - 2020 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

public class BaseJpaResourceProviderObservationDstu3 extends JpaResourceProviderDstu3<Observation> {

	/**
	 * Observation/$lastn
	 */
	@Operation(name = JpaConstants.OPERATION_LASTN, idempotent = true, bundleType = BundleTypeEnum.SEARCHSET)
	public IBundleProvider observationLastN(

		javax.servlet.http.HttpServletRequest theServletRequest,
		javax.servlet.http.HttpServletResponse theServletResponse,

		ca.uhn.fhir.rest.api.server.RequestDetails theRequestDetails,

		@Description(formalDefinition = "Results from this method are returned across multiple pages. This parameter controls the size of those pages.")
		@OperationParam(name = Constants.PARAM_COUNT)
			UnsignedIntType theCount,

		@Description(shortDefinition="The classification of the type of observation")
		@OperationParam(name="category")
			TokenAndListParam theCategory,

		@Description(shortDefinition="The code of the observation type")
		@OperationParam(name="code")
			TokenAndListParam theCode,

		@Description(shortDefinition="The subject that the observation is about (if patient)")
		@OperationParam(name="patient")
			ReferenceAndListParam thePatient,

		@Description(shortDefinition="The subject that the observation is about")
		@OperationParam(name="subject" )
			ReferenceAndListParam theSubject,

		@Description(shortDefinition="The maximum number of observations to return for each observation code")
		@OperationParam(name = "max", typeName = "integer", min = 0, max = 1)
			IPrimitiveType<Integer> theMax,

		@Sort
			SortSpec theSort

	) {
		startRequest(theServletRequest);
		try {
			SearchParameterMap paramMap = new SearchParameterMap();
			paramMap.add(Observation.SP_CATEGORY, theCategory);
			paramMap.add(Observation.SP_CODE, theCode);
			paramMap.add(Observation.SP_PATIENT, thePatient);
			paramMap.add(Observation.SP_SUBJECT, theSubject);
			paramMap.setLastNMax(theMax.getValue());
			if (theCount != null) {
				paramMap.setCount(theCount.getValue());
			}

			if (theSort != null) {
				paramMap.setSort(theSort);
			}

			return ((IFhirResourceDaoObservation<Observation>) getDao()).observationsLastN(paramMap, theRequestDetails, theServletResponse);
		} finally {
			endRequest(theServletRequest);
		}
	}

}
