package utils;


public class XPaths {
    public static String Hamburger = "//span[@class='cursor-pointer hidden lg:block']/div[contains(@class,'relative flex items-center z-50')]/span[@id='openloginnudge']";
    public  static String CarIcon = "//span[@id='desktop-nav-add-car-icon']";
    public static String  AddCarBoxOnHomepage = "//input[@id='carNumber']";
    public static String  MenuAddCar ="//div[contains(@class,'divide-y divide-custom-gray-border mt-2 px-4 lg:px-0')]/a[1]";
    public static String addCarButtonOnHome ="//div[@class='mt-0']//div[@id='add-car-button']/button[normalize-space(text())='Add car' and contains(@class, 'bg-primaryOrange')]";
    public static String AddCarIconOnMenu ="divide-y divide-custom-gray-border mt-2 px-4 lg:px-0')]/a[1]";
    public static String EnterCarNoInIframe ="//div[contains(@class,'rounded-lg h-[58px] flex items-center relative')]/input[@placeholder='DL 12 AA 1234']";
    public  static String AddCarButtonINIframe ="//div[@class='mt-6 lg:max-w-80']//div[@id='add-car-button']/button[normalize-space(text())='Add car' and contains(@class, 'bg-primaryOrange')]";
    public static String AddCarButtonOnListing ="//p[contains(@class, 'text-custom-gray-800') and normalize-space(text())='Essential Service Package']//following::button[contains(@class, 'rounded-xl') and contains(@class, 'text-primaryOrange') and normalize-space(text())='Add car to view price'][1]";
    public static String AddCarButtonOnPdp ="//button[contains(@class, 'rounded-xl') and contains(@class, 'bg-primaryOrange') and text()='Add car']";
    public static String ProdUrl ="";
}
