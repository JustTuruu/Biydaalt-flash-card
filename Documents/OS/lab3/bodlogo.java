import java.util.*;

public class bodlogo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    int problemNumber;

    if (args.length > 0) {
        // Try to parse the first argument as the problem number
        try {
            problemNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Буруу дугаар! Тоо оруулна уу.");
            scanner.close();
            return;
        }
    } else {
        System.out.println("Асуултын дугаарыг оруулна уу (1-6): ");
        problemNumber = scanner.nextInt();
    }
        
        switch (problemNumber) {
            case 1:
                solveProblem1(scanner);
                break;
            case 2:
                solveProblem2(scanner);
                break;
            case 3:
                solveProblem3(scanner);
                break;
            case 4:
                solveProblem4(scanner);
                break;
            case 5:
                solveProblem5(scanner);
                break;
            case 6:
                solveProblem6(scanner);
                break;
            default:
                System.out.println("Буруу дугаар!");
        }
        scanner.close();
    }

    // Асуулт 1: 2 CPU, 3 програм
    private static void solveProblem1(Scanner scanner) {
        int numCPUs = 2;
        int processesPerCPU = 2;
        System.out.println("3 программын ажиллах хугацаагыг мсек-ээр оруулна уу (10, 15, 30): ");
        int[] times = new int[3];
        for (int i = 0; i < 3; i++) {
            times[i] = scanner.nextInt();
        }
        Arrays.sort(times);
        // CPU бүрт хуваарилах: хамгийн урт хугацааны програмыг эхлээд өгөх
        int[] cpuLoads = new int[numCPUs];
        Arrays.fill(cpuLoads, 0);
        for (int i = times.length - 1; i >= 0; i--) {
            int minIndex = 0;
            for (int j = 1; j < numCPUs; j++) {
                if (cpuLoads[j] < cpuLoads[minIndex]) {
                    minIndex = j;
                }
            }
            cpuLoads[minIndex] += times[i];
        }
        int maxTime = 0;
        for (int time : cpuLoads) {
            if (time > maxTime) {
                maxTime = time;
            }
        }
        System.out.println("Бүх програм дуусах хугацаа: " + maxTime + " мсек");
    }

    // Асуулт 2: 3 CPU, 4 програм
    private static void solveProblem2(Scanner scanner) {
        int numCPUs = 3;
        int processesPerCPU = 2;
        System.out.println("4 программын ажиллах хугацаагыг мсек-ээр оруулна уу (6, 12, 15, 25): ");
        int[] times = new int[4];
        for (int i = 0; i < 4; i++) {
            times[i] = scanner.nextInt();
        }
        Arrays.sort(times);
        int[] cpuLoads = new int[numCPUs];
        Arrays.fill(cpuLoads, 0);
        for (int i = times.length - 1; i >= 0; i--) {
            int minIndex = 0;
            for (int j = 1; j < numCPUs; j++) {
                if (cpuLoads[j] < cpuLoads[minIndex]) {
                    minIndex = j;
                }
            }
            cpuLoads[minIndex] += times[i];
        }
        int maxTime = 0;
        for (int time : cpuLoads) {
            if (time > maxTime) {
                maxTime = time;
            }
        }
        System.out.println("Бүх програм дуусах хугацаа: " + maxTime + " мсек");
    }

    // Асуулт 3: Заавруудын CPI тооцоо
    private static void solveProblem3(Scanner scanner) {
        System.out.println("Заавруудын дарааллыг оруулна уу (A, Б, Б, Г, В, Г): ");
        String[] instructions = new String[6];
        for (int i = 0; i < 6; i++) {
            instructions[i] = scanner.next();
        }
        Map<String, Integer> cpiMap = new HashMap<>();
        cpiMap.put("A", 3);
        cpiMap.put("Б", 4);
        cpiMap.put("В", 2);
        cpiMap.put("Г", 5);
        
        int totalCycles = 0;
        for (String instr : instructions) {
            totalCycles += cpiMap.get(instr);
        }
        double cycleTimeNs = 15;
        double totalTimeNs = totalCycles * cycleTimeNs;
        System.out.println("Нийт хугацаа: " + totalTimeNs + " наносекунд");
    }

    // Асуулт 4: Өөр нэг CPI тооцоо
    private static void solveProblem4(Scanner scanner) {
        System.out.println("Заавруудын дарааллыг оруулна уу (A, В, Б, A, В): ");
        String[] instructions = new String[5];
        for (int i = 0; i < 5; i++) {
            instructions[i] = scanner.next();
        }
        Map<String, Integer> cpiMap = new HashMap<>();
        cpiMap.put("A", 2);
        cpiMap.put("Б", 4);
        cpiMap.put("В", 5);
        
        int totalCycles = 0;
        for (String instr : instructions) {
            totalCycles += cpiMap.get(instr);
        }
        double cycleTimeNs = 10;
        double totalTimeNs = totalCycles * cycleTimeNs;
        System.out.println("Нийт хугацаа: " + totalTimeNs + " наносекунд");
    }

    // Асуулт 5: 1 ГГц процессорын үзүүлэлт
    private static void solveProblem5(Scanner scanner) {
    System.out.print("Процессорын давтамжийг ГГц-ээр оруулна уу: ");
    double frequencyGHz = scanner.nextDouble();
    if (frequencyGHz <= 0) {
        System.out.println("Давтамж эерэг тоо байх ёстой!");
        return;
    }
    double cycleTimeNs = 1_000.0 / frequencyGHz;
    System.out.println("Циклын хугацаа: " + cycleTimeNs + " наносекунд");
}

// Асуулт 6: 15 ГГц процессорын үзүүлэлт (гарнаас авна)
private static void solveProblem6(Scanner scanner) {
    System.out.print("Процессорын давтамжийг ГГц-ээр оруулна уу: ");
    double frequencyGHz = scanner.nextDouble();
    if (frequencyGHz <= 0) {
        System.out.println("Давтамж эерэг тоо байх ёстой!");
        return;
    }
    double cycleTimeNs = 1_000.0 / frequencyGHz;
    System.out.println("Циклын хугацаа: " + cycleTimeNs + " наносекунд");
}
}