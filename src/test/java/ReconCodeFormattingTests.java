import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.util.containers.ContainerUtil;

import java.util.ArrayList;
import java.util.List;

public class ReconCodeFormattingTests extends LightJavaCodeInsightFixtureTestCase {
    public void testFormatting() {

        List<String> tests = new ArrayList<>();
        tests.add("attr-after-slot-expand.recon");
        tests.add("attr-collapse.recon");
        tests.add("attr-expand.recon");
        tests.add("attr-spaces-between.recon");
        tests.add("cellular-expand.recon");
        tests.add("client-config-expand.recon");
        tests.add("extant-slots.recon");
        tests.add("rosa-expand.recon");
        tests.add("slots-collapse-extant.recon");
        tests.add("tmforum-expand.recon");
        tests.add("trafficware-expand.recon");
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