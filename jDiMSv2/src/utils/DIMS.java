package utils;

import java.awt.Dimension;
import java.sql.SQLException;

import gui.FrameMain;
import gui.PanelCenter;
import gui.PanelCenterLogin;
import gui.PanelCenterMain;
import gui.PanelMainDetailContent;
import gui.PanelMenuLeft;
import gui.PanelMenuRight;
import gui.PanelTop;
import gui.PanelTopLogin;
import gui.PanelTopMain;
import models.Account;
import models.HostGroups;
import models.HostHistory;
import models.HostInGroup;
import models.MonitorHost;
import models.MonitorService;
import models.ServiceGroups;
import models.ServiceHistory;
import models.ServiceInGroup;
import models.ServiceInHost;
import models.ServiceInName;
import models.ServicesMatrix;

public class DIMS {
	private static DIMS instance = null;
	private String user;
	private String password;
	private DBConnection connector;
	private FrameMain screen;
	private RetrieveData picker;
	private Account account;
	private HostGroups hostgroup;
	private ServicesMatrix smt;
	private HostInGroup hing;
	private ServiceInHost sinh;
	private ServiceInGroup serviceInGroup;
	private ServiceInName serviceInName;
	private PanelCenterMain mainCenter;
	private MonitorHost detailHost;
	private MonitorService detailService;
	private HostHistory hostHistory;
	private ServiceHistory serviceHistory;
	
	private PanelMainDetailContent detailContent;
	private ServiceGroups serviceGroup;
	private PanelMenuLeft mainMenu;
	private PanelMenuRight rightMenu;
	private ShowHostGroups groupTabs;
	private PanelTopMain topMain;
	
	private Dimension screenSize;
	private Dimension mainScreenSize;
	
	private int currentOID;
	private int currentHostID;
	private int state;
	private String currentHostName;
	private String currentServiceName;
	private String currentGroupTab;
	private String [][] currentData;
	//Configuarion
	private String DBUsername;
	private String DBPassword;
	private String DBAddress;
	private int DBPort;
	private String icinga2status;
	
	private DIMS() throws SQLException {
		ParseConfiguration pc = ParseConfiguration.getInstance();
		DBUsername = pc.getDbUsername();
		DBPassword = pc.getDbPassword();
		DBAddress = pc.getDbAddress();
		DBPort = pc.getDbPort();
		connector = new DBConnection(DBUsername, DBPassword, DBAddress, DBPort);
		picker = new RetrieveData();
		currentOID = 0;
	}
	
	public static DIMS getInstance() throws SQLException {
		if (instance == null) {
			instance = new DIMS();
		}
		return instance;
	}
	
	public DBConnection getConnector(){
		return connector;
	}
	
	
	// ----------------- SET -----------------------------------
	public void setTopMain(PanelTopMain mainTop) {
		topMain = mainTop;
	}
	
	public void setCurrentData(String [][] data) {
		currentData = data;
	}
	public void setCoreStatus (String status) {
		icinga2status = status;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUser(String username) {
		this.user = username;
	}
	
	public void setMainMenu(PanelMenuLeft mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public void refreshHost(MonitorHost host){
		detailHost = host;
	}
	
	public void refreshMatrix(ServicesMatrix matrix){
		this.smt = matrix;
	}
	public void refreshService(MonitorService service){
		detailService = service;
	}
	
	public void setCenterMain(PanelCenterMain center){
		mainCenter = center;
	}
	
	public void setCurrentHostID(int hostOID){
		currentHostID = hostOID;
	}
	
	
	public void setCurrentObjectID(int OID){
		currentOID = OID;
	}
	
	public void setDetailContent(PanelMainDetailContent detailContent){
		this.detailContent = detailContent;
	}
	
	public void setCurrentHostName(String hostName) {
		this.currentHostName = hostName;
	}
	
	public void setCurrentServiceName(String serviceName) {
		this.currentServiceName = serviceName;
	}
	
	public void setMenuRight(PanelMenuRight menu) {
		rightMenu = menu;
	}
	
	public void setPanelGroups(ShowHostGroups groups) {
		groupTabs = groups;
	}
	
	public void setScreenSize(Dimension size) {
		this.screenSize = size;
	}
	
	public void setMainScreenSize(Dimension size) {
		this.mainScreenSize= size;
	}
	
	// -------------------- GET ------------------------
	public PanelTopMain getPanelTopMain() {
		return topMain;
	}
	
	public ServiceInName getServiceInName() {
		return serviceInName;
	}
	
	public ServiceInGroup getServiceInGroup() {
		return serviceInGroup;
	}
	
	public String [][] getCurrentData() {
		return currentData;
	}
	
	public String getCoreStatus() {
		return icinga2status;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Dimension getScreenSize() {
		return screenSize;
	}
	
	public Dimension getMainScreenSize() {
		return mainScreenSize;
	}
	public ShowHostGroups getPanelGroups() {
		return groupTabs;
	}
	
	public PanelMenuRight getMenuRight() {
		return rightMenu;
	}
	
	public String getCurrentHostName() {
		return currentHostName;
	}
	
	public String getCurrentServiceName() {
		return currentServiceName;
	}
	
	public int getCurrentHostID() {
		return currentHostID;
	}
	
	public PanelMenuLeft getMainMenu() {
		return mainMenu;
	}
	
	public int getCurrentOID(){
		return currentOID;
	}
	
	public PanelCenterMain getMainCenter(){
		return mainCenter;
	}
	
	public PanelMainDetailContent getDetailContent(){
		return detailContent;
	}
	public FrameMain getWindow(){
		return screen;
	}
	
	public RetrieveData getPicker(){
		return picker;
	}
	
	public Account getAccount(){
		return account;
	}
	
	public HostGroups getHostGroups(){
		return hostgroup;
	}
	
	public ServicesMatrix getServicesMatrix(){
		return smt;
	}
	
	public HostInGroup getHostInGroup(){
		return hing;
	}
	
	public ServiceInHost getServiceInHost(){
		return sinh;
	}
	
	public MonitorHost getHostPage(){
		return detailHost;
	}
	
	public MonitorService getServicePage(){
		return detailService;
	}
	
	public HostHistory getHostHistory(){
		return hostHistory;
	}
	
	public ServiceHistory getServiceHistory(){
		return serviceHistory;
	}
	
	public PanelCenterMain getCenterMain(){
		return mainCenter;
	}
	
	
	public int getState(){
		return state;
	}
	
	public ServiceGroups getServiceGroups(){
		return serviceGroup;
	}
	
	public void initModels() throws SQLException{
		hostgroup = new HostGroups();
		account = new Account();
		smt = new ServicesMatrix();
		hing = new HostInGroup();
		sinh = new ServiceInHost();
		detailHost = new MonitorHost();
		detailService = new MonitorService();
		hostHistory = new HostHistory();
		serviceHistory = new ServiceHistory();
		serviceGroup = new ServiceGroups();
		serviceInGroup = new ServiceInGroup();
		serviceInName = new ServiceInName();
	}
	
	public void showMainScreen() throws Exception {
		PanelTop top = new PanelTopLogin();
		PanelCenter center = new PanelCenterLogin();
		FrameMain mainScreen = new FrameMain(top, center);
		screen = mainScreen;
	}
}
