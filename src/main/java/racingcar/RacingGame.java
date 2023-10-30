package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    public void start() {

        List<Car> cars = new ArrayList<>();
        int tryCount;

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        inputCarName(cars);

        System.out.println("시도할 횟수는 몇회인가요?");
        tryCount = inputTry();

        //TODO: 각 차수별 전진 상태 출력 하기
        System.out.println("\n실행 결과");
        progress(cars, tryCount);

        //TODO: 우승자 출력 하기
    }

    public void inputCarName(List<Car> cars) {
        String[] carNames = Console.readLine().split(",");
        for (String name : carNames) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이내로 입력해 주세요");
            }
            if (name.contains(" ")) {
                name = name.replace(" ", "");
            }
            cars.add(new Car(name));
        }
    }

    public int inputTry() {
        String tryNumber = Console.readLine();
        if (!tryNumber.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자만 입력해 주세요");
        }
        return Integer.parseInt(tryNumber);
    }

    public void progress(List<Car> cars, int tryCount) {
        if (tryCount <= 0) {
            return;
        }
        for (Car car : cars) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            if (randomNumber >= 4) {
                car.state++;
            }
            System.out.print(car.name);
            System.out.print(" : ");
            System.out.println("-".repeat(car.state));
        }
        System.out.println();

        progress(cars, tryCount - 1);
    }

}
