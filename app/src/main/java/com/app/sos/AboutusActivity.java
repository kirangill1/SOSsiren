package com.app.sos;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        TextView txtDetails = (TextView) findViewById(R.id.txtDetails);
        txtDetails.setText("About ITI Limited\n" +
                "\n" +
                "ITI Limited, India’s first Public Sector Unit established in 1948 is the pioneer in the field of telecommunications. With the productive manufacturing units spread across five locations and a countrywide network of marketing/service outlets, the company offers a complete range of telecom products and total solutions covering the whole spectrum of Switching, Transmission, Access and Subscriber Premises equipment.\n" +
                "In the rapid growth towards technology the company is consolidating its diversification towards the Information and Communication Technology (ICT). Company is diversifying towards IOT, Smart city, Smart cards, Solar and telecom related products and services including turnkey project execution to offer complete solutions in different sectors. Secure communications are the company's forte with a proven record of engineering strategic communication networks for India's Defence forces. Extensive in-house R&D work is devoted towards design and development of encryption solutions to Indian Defence forces.\n" +
                "Security is the right of every citizen, working on this aspect ITI limited has set up a new brand, exclusively for the Surveillance products-REIT Brand providing solutions towards the safety and security with affordable schemes easing the citizen livelihood towards the safety measures.\n" +
                "About REIT\n" +
                "\n" +
                "REIT- a Surveillance brand under the emblem and collaboration with ITI Limited has been launched in association with Central PSU, Ministry of Communication, and Government of India. REIT, are the prime brand of surveillance products under ITI Limited. The brand is looking to create and cater the dedicated distribution towards the Government surveillance orders later extendable to open market needs.\n" +
                "REIT maintains a brand integrity and support of surveillance products backed with high global presence with expecting a market leading customer service, sales and support.\n" +
                "ITI limited-REIT brand has set up a launch, with zero VTS cost for any government demands of requirement. Initially the customers in need of a service have to get enrolled with a small amount of refundable security deposit. The services are rendered without the burden of initial hardware costs but with only on minimum affordable monthly charges towards software, server and data coming under the process. The amount is refundable in case the services are discontinued.\n" +
                "Who are we catering as services of surveillance?\n" +
                "As a mega initiative project, REIT brand is catering the needs of security surveillance products across towards Jammu Jan Suraksha Yojana in Jammu city providing the digital security to citizen in collaboration with Jammu Municipal Corporation. We are processing and providing the surveillance products manufactured under the ITI Limited- REIT brand towards the project.\n" +
                "Services of REIT Brand\n" +
                "\n" +
                "REIT Brand is catering towards the needs of the security solutions in the domains. The surveillance products with advance technology includes AHD and IP cameras, DVR, NVR, PTZ cameras, POE VTS, vehicle security products, and also the related equipments to cater the security needs.\n" +
                "•\tHome Security\n" +
                "•\tCommercial Security\n" +
                "•\tVehicle Security/li>\n" +
                "•\tGPS based tracking system\n" +
                "•\tCloud based Surveillance Systems\n" +
                "•\tOther IOT Products with the most innovative in advanced technology\n" +
                "REIT Security Council of India marketing division is looking forward in creating a dedicated distribution network to launch REIT brand in surveillance industry extended able to open market needs.\n" +
                "\n");
        txtDetails.setMovementMethod(new ScrollingMovementMethod());
    }
}
