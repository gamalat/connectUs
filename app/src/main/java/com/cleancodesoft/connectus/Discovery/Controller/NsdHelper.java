package com.cleancodesoft.connectus.Discovery.Controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class NsdHelper {

//    NsdManager manager;
//    String TAG = "NsdHelper";
//    String serviceName = "ConnectUs";
//    String SERVICE_TYPE = "_ConnectUs._tcp";
//    NsdServiceInfo mService;
//    NsdManager.ResolveListener resolveListener;
//    NsdManager.RegistrationListener registrationListener;
//    NsdManager.DiscoveryListener discoveryListener;
//    Context context;
//    public NsdHelper(Context applicationContext) {
//        this.context=applicationContext;
//        manager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
//    }
//
//
//    public void initializeNsd() {
//       initializeResolveListener();
//       //registerService(5000,context);
//        initializeResolveListener();
//
//    }
//    public void discoverServices() {
//
//        stopDiscovery();  // Cancel any existing discovery request
//        initializeDiscoveryListener();
//        manager.discoverServices(
//                SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener);
//    }
//    public void stopDiscovery() {
//        if (discoveryListener!= null) {
//            try {
//                manager.stopServiceDiscovery(discoveryListener);
//            } finally {
//            }
//            discoveryListener = null;
//        }
//    }
//    public void initializeResolveListener() {
//        resolveListener = new NsdManager.ResolveListener() {
//
//            @Override
//            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
//                // Called when the resolve fails. Use the error code to debug.
//                Log.d(TAG, "Resolve failed: " + errorCode);
//            }
//
//            @Override
//            public void onServiceResolved(NsdServiceInfo serviceInfo) {
//                Log.d(TAG, "Resolve Succeeded. " + serviceInfo);
//
//                if (serviceInfo.getServiceName().equals(serviceName)) {
//                    Log.d(TAG, "Same IP.");
//                    return;
//                }
//                mService = serviceInfo;
//                int port = mService.getPort();
//                InetAddress ip = mService.getHost();
//                Log.d("ippppp","ip"+ip);
////                portArr[counter++] = port;
////                IPArr[counter++] = ip;
//                Toast.makeText(context, "ip"+ip, Toast.LENGTH_SHORT).show();
//
//            }
//        };
//    }
//
//    public void initializeRegistrationListener() {
//        registrationListener = new NsdManager.RegistrationListener() {
//
//            @Override
//            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
//                // Save the service name. Android may have changed it in order to
//                // resolve a conflict, so update the name you initially requested
//                // with the name Android actually used.
//                serviceName = NsdServiceInfo.getServiceName();
//                Log.d(TAG, serviceName);
//            }
//
//            @Override
//            public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
//                // Registration failed! Put debugging code here to determine why.
//                Log.d(TAG, "ERROR " + errorCode);
//            }
//
//            @Override
//            public void onServiceUnregistered(NsdServiceInfo arg0) {
//                // Service has been unregistered. This only happens when you call
//                // NsdManager.unregisterService() and pass in this listener.
//            }
//
//            @Override
//            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
//                // Unregistration failed. Put debugging code here to determine why.
//            }
//        };
//    }
//
//    public void initializeDiscoveryListener() {
//
//        // Instantiate a new DiscoveryListener
//        discoveryListener = new NsdManager.DiscoveryListener() {
//
//            // Called as soon as service discovery begins.
//            @Override
//            public void onDiscoveryStarted(String regType) {
//                Log.d(TAG, "Service discovery started");
//            }
//
//            @Override
//            public void onServiceFound(NsdServiceInfo service) {
//                // A service was found! Do something with it.
//                Log.d(TAG, "Service discovery success | " + service);
//                if (!service.getServiceType().equals(SERVICE_TYPE)) {
//                    // Service type is the string containing the protocol and
//                    // transport layer for this service.
//                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
//                } else
//
//                if (service.getServiceName().equals(serviceName)) {
//                    // The name of the service tells the user what they'd be
//                    // connecting to. It could be "Bob's Chat App".
//                    Log.d(TAG, "Same machine: " + serviceName);
//                } else if (service.getServiceName().contains("NsdChat")){
//                    Log.d(TAG, serviceName);
//                    Log.d(TAG, service.getServiceName());
//                    Log.d(TAG, service.getServiceType());
//                    manager.resolveService(service, resolveListener);
//                }
//            }
//
//            @Override
//            public void onServiceLost(NsdServiceInfo service) {
//                // When the network service is no longer available.
//                // Internal bookkeeping code goes here.
//                Log.d(TAG, "service lost: " + service);
//            }
//
//            @Override
//            public void onDiscoveryStopped(String serviceType) {
//                Log.d(TAG, "Discovery stopped: " + serviceType);
//            }
//
//            @Override
//            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
//                Log.d(TAG, "Discovery failed: Error code:" + errorCode);
//                manager.stopServiceDiscovery(this);
//            }
//
//            @Override
//            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
//                Log.d(TAG, "Discovery failed: Error code:" + errorCode);
//                manager.stopServiceDiscovery(this);
//            }
//        };
//    }

    public static final String KEY_SERVICE_INFO = "serviceinfo";

    Context mContext;
    NsdManager mNsdManager;
    NsdManager.ResolveListener mResolveListener;
    NsdManager.DiscoveryListener mDiscoveryListener;
    NsdManager.RegistrationListener mRegistrationListener;

    public static final String SERVICE_TYPE = "_ConnectUs._tcp";

    public static final String SERVICE_TYPE_PLUS_DOT = SERVICE_TYPE + ".";

    public static final String TAG = "NSDHelper: ";

    private static final String DEFAULT_SERVICE_NAME = "ConnectUs";
    public String mServiceName = DEFAULT_SERVICE_NAME;

    NsdServiceInfo mService;
    public static List<InetAddress> users_IPs = new ArrayList<>();//دى اللى هخزن فيها ip اللى جايه من الديسكفر
    DiscoveryInterface discoveryInterface;

    public NsdHelper(Context context, DiscoveryInterface discoveryInterface) {
        mContext = context;
        this.discoveryInterface = discoveryInterface;
        mNsdManager = (NsdManager) context.getSystemService(Context.NSD_SERVICE);
    }

    //public void initializeNsd() {
    //    initializeResolveListener();
    //}

    public void initializeDiscoveryListener() {
        mDiscoveryListener = new NsdManager.DiscoveryListener() {
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d(TAG, "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                Log.d(TAG, "Service discovery success" + service);
                String serviceType = service.getServiceType();
                Log.d(TAG, "Service discovery success: " + service.getServiceName());
                boolean isOurService = serviceType.equals(SERVICE_TYPE) || serviceType.equals
                        (SERVICE_TYPE_PLUS_DOT);

                if (!isOurService) {
                    Log.d(TAG, "Unknown Service Type: " + service.getServiceType());
                } else if (service.getServiceName().equals(mServiceName)) {
                    Log.d(TAG, "Same machine: " + mServiceName);
                } else if (service.getServiceName().contains(mServiceName)) {
                    Log.d(TAG, "different machines. (" + service.getServiceName() + "-" +
                            mServiceName + ")");
                    //mNsdManager.resolveService(service, mResolveListener);
                    mNsdManager.resolveService(service, new resolveListener());
                }
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                Log.e(TAG, "service lost" + service);
                if (mService == service) {

                    mService = null;

                }
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i(TAG, "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e(TAG, "Discovery failed: Error code:" + errorCode);
            }
        };
    }

//    public void initializeResolveListener() {
//        mResolveListener = new NsdManager.ResolveListener() {
//            @Override
//            public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
//                Log.e(TAG, "Resolve failed" + errorCode);
//            }
//
//            @Override
//            public void onServiceResolved(NsdServiceInfo serviceInfo) {
//                Log.d(TAG, "Resolve Succeeded. " + serviceInfo);
//                if (serviceInfo.getServiceName().equals(mServiceName)) {
//                    Log.d(TAG, "Same IP.");
//                    return;
//                }
//                mService = serviceInfo;
//                mService = serviceInfo;
//                int port = mService.getPort();
//                InetAddress ip = mService.getHost();
////                mService.getAttributes().
////                TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
////                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
////                    // TODO: Consider calling
////                    //    ActivityCompat#requestPermissions
////                    // here to request the missing permissions, and then overriding
////                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                    //                                          int[] grantResults)
////                    // to handle the case where the user grants the permission. See the documentation
////                    // for ActivityCompat#requestPermissions for more details.
////                    return;
////                }
////                String uid = tManager.getDeviceId();
//                Log.d("ipppppp", "ip" + ip);
//                Log.d("eeeeee", "port" + port);
//               // Log.d("ipppppp", "ip" +  uid);
//                boolean exists = false;
//                for (InetAddress inetAddress : users_IPs) {
//                    if (inetAddress.equals(ip)) {
//                        exists = true;
//                    }
//                }
//                if (!exists) {
//                    users_IPs.add(ip);
//                    discoveryInterface.onDiscover(users_IPs);
//                }
//            }
//        };
//    }

    public void initializeRegistrationListener() {
        mRegistrationListener = new NsdManager.RegistrationListener() {
            @Override
            public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
                String ServiceName  = NsdServiceInfo.getServiceName();
                Log.d(TAG, "Service registered: " + NsdServiceInfo);
                // NotificationToast.showToast(mContext, "Service registered");
                Toast.makeText(mContext, "Service registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRegistrationFailed(NsdServiceInfo arg0, int arg1) {
                Log.d(TAG, "Service registration failed: " + arg1);
                //    NotificationToast.showToast(mContext, "Service registration failed");
                Toast.makeText(mContext, "Service registration failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceUnregistered(NsdServiceInfo arg0) {
                Log.d(TAG, "Service unregistered: " + arg0.getServiceName());
                // NotificationToast.showToast(mContext, "Service unregistered");
                Toast.makeText(mContext, "Service unregistered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
                Log.d(TAG, "Service unregistration failed: " + errorCode);
                //NotificationToast.showToast(mContext, "Service un-registration failed");
                Toast.makeText(mContext, "Service un-registration failed", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void registerService(int port) {
        tearDown();  // Cancel any previous registration request
        initializeRegistrationListener();
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setPort(port);
        serviceInfo.setServiceName(mServiceName);
        serviceInfo.setServiceType(SERVICE_TYPE);
        Log.v(TAG, Build.MANUFACTURER + " registering service: " + port);
        mNsdManager.registerService(
                serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener);
    }

    public void discoverServices() {
        stopDiscovery();  // Cancel any existing discovery request
        initializeDiscoveryListener();
        mNsdManager.discoverServices(
                SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);
    }

    public void stopDiscovery() {
        if (mDiscoveryListener != null) {
            try {
                mNsdManager.stopServiceDiscovery(mDiscoveryListener);
            } finally {
            }
            mDiscoveryListener = null;
        }
    }

    public void tearDown() {
        if (mRegistrationListener != null) {
            try {
                mNsdManager.unregisterService(mRegistrationListener);
            } finally {
            }
            mRegistrationListener = null;
        }
    }
    private  class  resolveListener implements NsdManager.ResolveListener{

        @Override
        public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode) {
            Log.e(TAG, "Resolve failed" + errorCode);
        }

        @Override
        public void onServiceResolved(NsdServiceInfo serviceInfo) {
            Log.d(TAG, "Resolve Succeeded. " + serviceInfo);
            if (serviceInfo.getServiceName().equals(mServiceName)) {
                Log.d(TAG, "Same IP.");
                return;
            }
            mService = serviceInfo;
            mService = serviceInfo;
            int port = mService.getPort();
            InetAddress ip = mService.getHost();
    //                mService.getAttributes().
//                    TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
    //                String uid = tManager.getDeviceId();
           Log.d("ipppppp", "ip" + ip);
//            Log.d("eeeeee", "port" + port);
            // Log.d("ipppppp", "ip" +  uid);
            boolean exists = false;
            for (InetAddress inetAddress : users_IPs) {
                if (inetAddress.equals(ip)) {
                    exists = true;
                }
            }
            if (!exists) {
                users_IPs.add(ip);
                discoveryInterface.onDiscover(users_IPs);
            }
            for (int i = 0; i < users_IPs.size(); i++) {
                Log.d("ipppppp", "ip" + users_IPs.get(i));
            }

        }
    }
}
