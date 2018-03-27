package com.example.gabrielepanarello.jsonrequest;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Gabriele Panarello on 27/03/2018.
 */

public class DeviceOutput {

    private String rom;
    private String screenSize;
    private String backCamera;
    private String companyName;
    private String name;
    private String frontCamera;
    private String battery;
    private String operatingSystem;
    private String processor;
    private String url;
    private String ram;

    public DeviceOutput(String rom, String screenSize, String backCamera, String companyName, String name, String frontCamera, String battery, String operatingSystem, String processor, String url, String ram) {
        this.rom = rom;
        this.screenSize = screenSize;
        this.backCamera = backCamera;
        this.companyName = companyName;
        this.name = name;
        this.frontCamera = frontCamera;
        this.battery = battery;
        this.operatingSystem = operatingSystem;
        this.processor = processor;
        this.url = url;
        this.ram = ram;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getName() {
        return name;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }
}
