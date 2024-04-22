import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetComputerSpec {
    public String getOSInfo() {
        String os = System.getProperty("os.name").toLowerCase();

        System.out.println("운영 체제 : " + os);

        return os;
    }

    public String getGPUInfo(String os) {
        String gpuInfo = "지원하지 않는 운영체제이거나 그래픽 카드(GPU)가 존재하지 않습니다.";
        if (os.contains("win")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("wmic path win32_VideoController get name");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty() && !line.startsWith("Name")) {
                        gpuInfo = "그래픽 카드(GPU) : " + line.trim();
                        System.out.println(gpuInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }

        } else if (os.contains("mac")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("system_profiler SPDisplaysDataType");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty() && line.contains("Chipset Model")) {
                        gpuInfo = "그래픽 카드(GPU) : " + line.split(":")[1].trim();
                        System.out.println(gpuInfo); // GPU 정보 출력
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("lspci | grep VGA");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty()) {
                        gpuInfo = "그래픽 카드(GPU) : " + line.trim();
                        System.out.println(gpuInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        return gpuInfo;
    }

    public String getCPUInfo(String os) {
        String cpuInfo = "지원하지 않는 운영체제이거나 프로세서(CPU)가 존재하지 않습니다.";
        if (os.contains("win")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("wmic cpu get name");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty() && !line.startsWith("Name")) {
                        cpuInfo = "프로세서(CPU) : " + line.trim();
                        System.out.println(cpuInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (os.contains("mac")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("sysctl -n machdep.cpu.brand_string");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty() && !line.startsWith("Name")) {
                        cpuInfo = "프로세서(CPU) : " + line.trim();
                        System.out.println(cpuInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("lscpu | grep 'Model name'");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (!line.isEmpty() && !line.startsWith("Name")) {
                        cpuInfo = "프로세서(CPU) : " + parts[1].trim();
                        System.out.println(cpuInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        return cpuInfo;
    }

    public String getRAMInfo(String os) {
        String ramInfo = "지원하지 않는 운영체제이거나 메모리(RAM)가 존재하지 않습니다.";
        if (os.contains("win")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("wmic ComputerSystem get TotalPhysicalMemory");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if (!line.isEmpty() && !line.startsWith("TotalPhysicalMemory")) {
                        double memoryBytes = Double.parseDouble(line.trim());
                        double memoryGB = memoryBytes / (1024 * 1024 * 1024);
                        ramInfo = String.format("메모리(RAM) : %.1f GB", memoryGB);
                        System.out.println(ramInfo);
                        break;
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (os.contains("mac")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("sysctl hw.memsize");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                if ((line = input.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        double memoryBytes = Double.parseDouble(parts[1].trim());
                        double memoryGB = memoryBytes / (1024 * 1024 * 1024);
                        ramInfo = String.format("메모리(RAM) : %.1f GB", memoryGB);
                        System.out.println(ramInfo);
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            try {
                String line;
                Process process = Runtime.getRuntime().exec("free -m | grep Mem");
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                if ((line = input.readLine()) != null) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1) {
                        double memoryMB = Double.parseDouble(parts[1].trim());
                        double memoryGB = memoryMB / 1024;
                        ramInfo = String.format("메모리(RAM) : %.1f GB", memoryGB);
                        System.out.println(ramInfo);
                    }
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        return ramInfo;
    }

    public String getDiskInfo(String os) {
        StringBuilder diskInfoBuilder = new StringBuilder();
        if (os.contains("win")) {
            try {
                Process process = Runtime.getRuntime().exec("wmic logicaldisk get size,freespace,caption");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                System.out.println("파일시스템 | 남은 공간(GB) | 전체 크기(GB)");
                diskInfoBuilder.append("드라이브 | 남은 공간(GB) | 전체 크기(GB)\n");
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty() || line.startsWith("Caption")) {
                        continue;
                    }
                    String[] tokens = line.split("\\s+");
                    if (tokens.length >= 3) {
                        String caption = tokens[0];
                        long freeSpace = Long.parseLong(tokens[1].trim());
                        long size = Long.parseLong(tokens[2].trim());

                        double freeSpaceGB = freeSpace / (1024.0 * 1024.0 * 1024.0);
                        double sizeGB = size / (1024.0 * 1024.0 * 1024.0);

                        System.out.format("%s | %.1fGB | %.1fGB%n", caption, freeSpaceGB, sizeGB);
                        diskInfoBuilder.append(String.format("%s | %.1fGB | %.1fGB%n", caption, freeSpaceGB, sizeGB));
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("mac")) {
            try {
                Process process = Runtime.getRuntime().exec("df -h");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                System.out.println("파일시스템 | 남은 공간 | 전체 크기");
                diskInfoBuilder.append("파일시스템 | 남은 공간 | 전체 크기\n");
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty() || !line.contains("/")) {
                        continue;
                    }
                    String[] tokens = line.split("\\s+");
                    if (tokens.length >= 5) {
                        String caption = tokens[0];
                        String size = tokens[1];
                        String freeSpace = tokens[3];

                        System.out.format("%s | %s | %s%n", caption, freeSpace, size);
                        diskInfoBuilder.append(String.format("%s | %s | %s%n", caption, freeSpace, size));
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return diskInfoBuilder.toString();
    }
}