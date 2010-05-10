package org.osaf.caldav4j;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.webdav.lib.Ace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.osaf.caldav4j.exceptions.CalDAV4JException;

public class CalDAVCollectionTest_ACL extends BaseTestCase {
	public CalDAVCollectionTest_ACL() {
		super();
	}

	protected static final Log log = LogFactory
	.getLog(CalDAVCollectionTest_ACL.class);
	
	@Before
	public void setUp() throws Exception {
		super.setUp();

		try {
			mkcalendar(COLLECTION_PATH); 
		} catch (Exception e) {
			e.printStackTrace();
			log.info("MKCOL unsupported?", e);
		}
		caldavPut(ICS_GOOGLE_DAILY_NY_5PM_PATH);
		caldavPut(ICS_GOOGLE_ALL_DAY_JAN1_PATH);
		caldavPut(ICS_GOOGLE_NORMAL_PACIFIC_1PM_PATH);
		caldavPut(ICS_GOOGLE_SINGLE_EVENT_PATH);

	}

	@After
	public void tearDown() throws Exception {

		caldavDel(ICS_GOOGLE_DAILY_NY_5PM_PATH);
		caldavDel(ICS_GOOGLE_ALL_DAY_JAN1_PATH);
		caldavDel(ICS_GOOGLE_NORMAL_PACIFIC_1PM_PATH);
		caldavDel(ICS_GOOGLE_SINGLE_EVENT_PATH);
		try {
			del(COLLECTION_PATH);
		} catch (Exception e) {
			log.info("DELETE Collection unsupported", e);
		}
	}


	@Test
	public void getFolderAces() throws CalDAV4JException {
		CalDAVCollection calendarCollection = createCalDAVCollection();
		Ace[] aces = calendarCollection.getAces(httpClient, null);
		assertNotNull(aces);		
		log.info(aces);			
	}
	
	@Test
	public void getResourceAces() throws Exception {		
		CalDAVCollection calendarCollection = createCalDAVCollection();		
		Ace[] aces = calendarCollection.getAces(httpClient, ICS_GOOGLE_DAILY_NY_5PM);
		assertNotNull(aces);		
		log.info(aces);	
	}
	
	// @Test TODO
	public void setFolderAces() {
		
	}

	// @Test TODO
	public void updateFolderAces() {
		
	}
}
