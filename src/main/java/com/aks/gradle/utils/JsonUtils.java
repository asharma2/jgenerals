package com.aks.gradle.utils;

public class JsonUtils {

	public static final String getMyWatchlistJson(String watchlistId) {
		return "{\"lsmMetricCodes\":[],\"metricCodes\":[\"COMPANYID\",\"COMPANY_NAME\",\"COUNTRYISOCODE\",\"ERRORCODE\",\"ERRORMESSAGE\",\"CURRENCYCODE\",\"IS_GLOBAL_SCALE\",\"PG_SUPPORT_NOTCHES_UPLIFT_LOCAL\",\"PG_LABEL\",\"PG_SUPPORT_NOTCHES_UPLIFT\",\"FINANCIAL_STATEMENT_DATE\",\"INDUSTRY_CLASSIFICATION_CODE\",\"DF_1_YEAR\",\"PG_SUPPORT_CAPPED_DF_1_YEAR\",\"PG_SUPPORT_CAPPED_SCORE\",\"PG_SUPPORT_CAPPED_SCORE_LOCAL\",\"CREDITSCORE\",\"CREDITSCORE_LOCAL\"],"
		        + "\"modelFamilyId\":2," + "\"watchListDataSource\":\"LA\"," + "\"watchListId\":" + watchlistId + "}";
	}
}
