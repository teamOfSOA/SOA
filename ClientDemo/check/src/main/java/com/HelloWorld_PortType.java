/**
 * HelloWorld_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com;

public interface HelloWorld_PortType extends java.rmi.Remote {
    public java.lang.String sendMailTo(java.lang.String targetAddress) throws java.rmi.RemoteException;
    public java.lang.String checkCode(java.lang.String inputCode) throws java.rmi.RemoteException;
}
