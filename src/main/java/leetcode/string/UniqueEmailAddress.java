package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
929. Unique Email Addresses
Easy

631

165

Add to List

Share
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address,
mail sent there will be forwarded to the same address without dots in the local name.
For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored.
This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?


Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 */

@Easy
@StringTag
public class UniqueEmailAddress {

    public static int numUniqueEmails(String[] emails) {
        Map<String, Set<String>> lookup = new HashMap<>();

        for (String email : emails) {
            String[] emailSplit = email.split("@");
            String domain = emailSplit[1];
            String user = emailSplit[0];
            String uniqueUserWithDot = user.split("\\+")[0];
            String[] uniqueUserPart = uniqueUserWithDot.split("\\.");
            String userName = "";
            for (String part : uniqueUserPart) {
                userName += part;
            }

            if (!lookup.containsKey(domain)) {
                Set<String> set = new HashSet<>();
                set.add(userName);
                lookup.put(domain, set);
            } else {
                if (!lookup.get(domain).contains(userName)) {
                    lookup.get(domain).add(userName);
                }
            }
        }

        int sum = 0;

        for (Map.Entry<String, Set<String>> a : lookup.entrySet()) {
            sum += a.getValue().size();
        }

        return sum;
    }

    // one set solution
    public int numUniqueEmails_oneSet(String[] emails) {
        Set<String> normalized = new HashSet<>(); // used to save simplified email address, cost O(n) sapce.
        for (String email : emails) {
            String[] parts = email.split("@"); // split into local and domain parts.
            String[] local = parts[0].split("\\+"); // split local by '+'.
            normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.
        }
        return normalized.size();
    }

    public static void main(String[] args) {
        String[] test = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        numUniqueEmails(test);
    }
}
