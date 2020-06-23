package domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validate(lottos);
        this.lottos = lottos;
    }

    private void validate(List<Lotto> lottos) {
        if (lottos == null || lottos.isEmpty()) {
            throw new IllegalArgumentException("로또는 1개 이상 구매해야 합니다.");
        }
    }
}
