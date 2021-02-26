package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatisticResult;
import lotto.domain.Lottos;
import lotto.domain.PayAmount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachineController {

    private final InputView inputView;

    private final OutputView outputView;

    public LottoMachineController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void purchase() {
        Lottos lottos = getInputLottos();
        WinningLotto winningLotto = getInputWinningLotto();
        LottoStatisticResult result = lottos.match(winningLotto);
        outputView.printStatisticResult(result);
    }

    private Lottos getInputLottos() {
        PayAmount payAmount = inputView.readPayAmount();
        LottoCount lottoCount = payAmount.calculateLottoCount();
        LottoCount manualLottoCount = inputView.readManualLottoCount();
        LottoCount autoLottoCount = lottoCount.subtract(manualLottoCount);

        outputView.printInputManualLottoNumbers();
        List<Lotto> manualLottos = getInputManualLottos(manualLottoCount);

        outputView.printPurchasingLotto(manualLottoCount.getCount(), autoLottoCount.getCount());
        Lottos autoLottos = Lottos.from(autoLottoCount.getCount());
        outputView.printLottos(autoLottos);

        Lottos lottos = autoLottos.concat(manualLottos);
        return lottos;
    }

    private List<Lotto> getInputManualLottos(LottoCount count) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < count.getCount(); i++) {
            List<Integer> lottoNumbers = inputView.readManualLottoNumbers();
            manualLottos.add(Lotto.fromNumbers(lottoNumbers));
        }
        return manualLottos;
    }

    private WinningLotto getInputWinningLotto() {
        List<Integer> lotto = inputView.readWinningLotto();
        LottoNumber bonusNumber = inputView.readBonusNumber();
        return WinningLotto.of(lotto, bonusNumber);
    }
}
