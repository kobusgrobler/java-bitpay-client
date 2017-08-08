package test;

import controller.BitPay;
import controller.BitPayException;
import model.Bill;
import model.BillItem;
import model.Subscription;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertTrue;

/**
 *
 * Created by kobus on 2017/08/08.
 */
public class SubscriptionTest {

	private static final BitPayLogger logger = new BitPayLogger(BitPayLogger.DEBUG);

    private BitPay bitpay;
    private static String clientName = "BitPay Java Library Tester2";
    private static URI myKeyFile;

    @Before
    public void setUp() throws BitPayException {
        //ensure the second argument (api url) is the same as the one used in setUpOneTime()
        bitpay = new BitPay(clientName, BitPay.BITPAY_TEST_URL);
    }

	@BeforeClass
	public static void setUpOneTime() throws Exception
	{
		// If this test has never been run before then this test must be run twice in order to pass.
		// The first time this test runs it will create an identity and emit a client pairing code.
		// The pairing code must then be authorized in a BitPay account.  Running the test a second
		// time should result in the authorized client (this test) running to completion.
        myKeyFile = new URI("file:///tmp/bitpay_private.key"); //if file exists, it will not overwrite

        clientName += " on " + java.net.InetAddress.getLocalHost();
        BitPay bitpay = new BitPay(myKeyFile,clientName, BitPay.BITPAY_TEST_URL); //this tests the old way of creating keys/clients
        
        if (!bitpay.clientIsAuthorized(BitPay.FACADE_MERCHANT))
        {
            String pairingCode = bitpay.requestClientAuthorization(BitPay.FACADE_MERCHANT);
            
            // Signal the device operator that this client needs to be paired with a merchant account.
            logger.info("Client is requesting MERCHANT facade access. Go to " + BitPay.BITPAY_TEST_URL + " and pair this client with your merchant account using the pairing code: " + pairingCode);
            throw new BitPayException("Error: client is not authorized.");
        }
	}

    @Test
    public void testSubscriptions() throws BitPayException,IOException {
        try (BitPay bitpay = new BitPay(clientName, BitPay.BITPAY_TEST_URL)) {

            Subscription newSub = new Subscription();
            newSub.setSchedule("monthly");
            newSub.setStatus(Subscription.STATUS_ACTIVE);

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            long date = cal.getTimeInMillis();
            newSub.setNextDelivery(date);

            Bill newBill = new Bill();
            newSub.setBillData(newBill);
            newBill.setCurrency("USD");
            newBill.setNumber("3");
            newBill.setDueDate(date);
            newBill.setEmail("satoshi@bitpay.com");
            ArrayList<BillItem> items = new ArrayList<>(1);
            newBill.setItems(items);
            BillItem item = new BillItem();
            item.setDescription("Community Keepers Donation");
            item.setPrice(10.00);
            item.setQuantity(1);
            items.add(item);
            Subscription created = bitpay.createSubscription(newSub);
            assertTrue(created.getId() != null);

            List<Subscription> subscriptions=bitpay.getSubscriptions();
            boolean found = false;
            for (Subscription subscription : subscriptions) {
                logger.info("subscription:"+subscription);
                if (subscription.getId().equals(created.getId())) {
                    assertTrue(subscription.getStatus().equals(Subscription.STATUS_ACTIVE));
                    assertTrue(subscription.getNextDelivery() == date);
                    found = true;
                }
            }
            assertTrue(found);

            created.setStatus(Subscription.STATUS_CANCELLED);
            Subscription updated = bitpay.updateSubscription(created);
            logger.info("updated subscription:"+updated);
            assertTrue(updated.getStatus().equals(Subscription.STATUS_CANCELLED));

            Subscription fetched = bitpay.getSubscription(created.getId());
            logger.info("fetched subscription:"+fetched);
            assertTrue(fetched.getId().equals(created.getId()));

            List<Bill> bills=bitpay.getBills();
            for (Bill bill :
                    bills) {
                logger.info("bill:"+bill);
            }
        }
    }
}
