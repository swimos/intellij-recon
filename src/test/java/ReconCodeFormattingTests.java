import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.util.containers.ContainerUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReconCodeFormattingTests extends LightJavaCodeInsightFixtureTestCase {
    @Test
    public void testFormatting() {
        List<String> tests = new ArrayList<>();
        tests.add("attr-after-slot-expand.recon");
        tests.add("attr-collapse.recon");
        tests.add("attr-expand.recon");
        tests.add("attr-in-attr-header-expand.recon");
        tests.add("attr-nested-body-collapse.recon");
        tests.add("attr-nested-collapse.recon");
        tests.add("attr-nested-expand.recon");
        tests.add("attr-nested-format.recon");
        tests.add("attr-record-collapse.recon");
        tests.add("attr-record-no-change.recon");
        tests.add("attr-spaces-between.recon");
        tests.add("attr-slot-expand.recon");
        tests.add("cellular-collapse.recon");
        tests.add("cellular-expand.recon");
        tests.add("client-config-collapse.recon");
        tests.add("client-config-expand.recon");
        tests.add("comment-collapse.recon");
        tests.add("extant-slots.recon");
        tests.add("operations-collapse.recon");
        tests.add("record-after-literal.recon");
        tests.add("rosa-expand.recon");
        tests.add("slot-after-attr-collapse.recon");
        tests.add("slots-collapse-extant.recon");
        tests.add("tmforum-expand.recon");
        tests.add("trafficware-expand.recon");
        tests.add("transit-collapse.recon");
        tests.add("transit-expand.recon");
        tests.add("warehouse-expand.recon");

        for (String test : tests) {
            myFixture.configureByFile("src/test/testData/formatting/input/" + test);
            WriteCommandAction.writeCommandAction(getProject()).run(() ->
                    CodeStyleManager.getInstance(getProject()).reformatText(
                            myFixture.getFile(),
                            ContainerUtil.newArrayList(myFixture.getFile().getTextRange())
                    )
            );
            myFixture.checkResultByFile("src/test/testData/formatting/expected/" + test);
        }


    }
}
