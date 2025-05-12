import java.util.HashMap;
public class Main {

  public static void main(String[] args) {
    InsuranceProductList insuranceProductList = new InsuranceProductListImpl();
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    int chooseMenu =0;

    while(true){
      System.out.println("Product Manager Menu");
      System.out.println("1.상품 등록");
      System.out.println("2.상품 수정");
      System.out.println("3.상품 조회");
      System.out.println("4.상품 삭제");
      System.out.println("5.종료");
      chooseMenu = scanner.nextInt();
      scanner.nextLine();
      switch(chooseMenu){
        case 1:
          createMenu(insuranceProductList,scanner);
          break;
        case 2:
          updateMenu(insuranceProductList,scanner);
          break;
        case 3:
          searchMenu(insuranceProductList,scanner);
          break;
        case 4:
          deleteMenu(insuranceProductList,scanner);
          break;
        case 5:
          System.out.println("프로그램을 종료합니다.");
          System.exit(0);
          break;
        default:
          System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
          break;
      }
    }


  }

  public static void deleteMenu(InsuranceProductList insuranceProductList,java.util.Scanner scanner){
    ProductManagement manager = new ProductManagement();
    String input = null;
    boolean result = false;

    while(true){
      System.out.println("삭제하려는 상품의 ID를 입력해주세요.");
      input = scanner.nextLine();
      if(input!=null){
        result = manager.deleteProduct(insuranceProductList,input);
        break;
      }
    }
    if(result)
      System.out.println("삭제가 완료되었습니다.");
    else
      System.out.println("문제가 발생했습니다.");
  }

  /**
   * 상품 조회
   * @param insuranceProductList
   * @param scanner
   */
  public static void searchMenu(InsuranceProductList insuranceProductList,java.util.Scanner scanner){
    ProductManagement manager = new ProductManagement();
    int index = 0;
    final int maxCount = 10;
    String input = "";

    if(insuranceProductList.size()==0){
      System.out.println("등록된 상품이 없습니다.");
      return;
    }

    while(index < insuranceProductList.size()){
      int end = Math.min(index+maxCount,insuranceProductList.size());
      System.out.println("=== 보험 상품 ===");
      for(int i=index;i<end;i++){
        InsuranceProduct product = insuranceProductList.getProduct(i);
        System.out.println(index+1+". "+ product.getProductID()+" "+product.getProductName()+" "+product.getProductManagementID());
      }
      index=end;
      System.out.println("조회하고 싶은 상품을 선택해주세요.");
      if(index>=insuranceProductList.size())
        System.out.println("모든 상품을 다 출력했습니다.");
      else
        System.out.println("다음 페이지로 넘어가려면 'next', 키워드 검색을 원하시면 'search', 조회 종료를 원하시면 'end'를 입력해주세요.");
      input = scanner.nextLine();
      if(input.equals("search")){
        searchKeyWord(insuranceProductList,scanner);
        break;
      }else if(input.equals("end")){
        System.out.println("상품 조회를 종료합니다.");
        break;
      }else if(!input.equals("next")){
        try{
          index = Integer.parseInt(input);
        }catch(NumberFormatException e){
          index = checkIntInput(scanner,"잘못된 입력입니다. 번호를 다시 입력해주세요 ");
        }
        System.out.println(manager.getProduct(insuranceProductList,index-1).toString());
        break;
      }
    }
  }

  /**
   * 상품 키워드 조회
   * @param insuranceProductList
   * @param scanner
   */
  public static void searchKeyWord(InsuranceProductList insuranceProductList,java.util.Scanner scanner){
    ProductManagement manager = new ProductManagement();
    InsuranceProductList products = null;

    System.out.println("원하는 키워드를 선택해주세요.");
    System.out.println("1.product id");
    System.out.println("2.product name");
    System.out.println("3.product management id");
    int chooseMenu = scanner.nextInt();
    scanner.nextLine();

    switch (chooseMenu){
      case 1:
        System.out.println("찾으려는 상품의 ID를 입력하세요 : ");
        String checkProductID = scanner.nextLine();
        products = manager.searchProducts(insuranceProductList,"productID",checkProductID);
        break;
      case 2:
        System.out.println("찾으려는 상품의 이름을 입력하세요 : ");
        String checkProductName = scanner.nextLine();
        products = manager.searchProducts(insuranceProductList,"productName",checkProductName);
        break;
      case 3:
        System.out.println("찾으려는 상품의 상품관리자 id를 입력하세요 : ");
        String checkProductManagerID = scanner.nextLine();
        products = manager.searchProducts(insuranceProductList,"productManagementID",checkProductManagerID);
        break;
    }

    insuranceProductList.printAllProducts();

  }

  /**
   * 상품 수정 메뉴
   * @param insuranceProductList
   * @param scanner
   */
  public static void updateMenu(InsuranceProductList insuranceProductList, java.util.Scanner scanner){
    System.out.println("업데이트하려는 상품의 ID를 적어주세요 : ");
    String productID = scanner.nextLine();

    ProductManagement manager = new ProductManagement();
    InsuranceProduct product = manager.searchProduct(insuranceProductList,productID);
    if(product==null){
      System.out.println("일치하는 상품이 없습니다.");
      return;
    }

    System.out.println("수정하려는 정보를 선택해주세요.");
    System.out.println("1.product name");
    System.out.println("2.max age");
    System.out.println("3.max number event");
    System.out.println("4.premium");
    System.out.println("5.reduction period");
    System.out.println("6.reduction ratio");
    System.out.println("7.sex");
    System.out.println("8.exemption period");
    System.out.println("9.coverage by age");
    int chooseMenu = scanner.nextInt();
    scanner.nextLine();
    System.out.println("수정된 값을 입력해주세요 : ");
    boolean result = false;

    switch (chooseMenu){
      case 1:
        result = product.setProductName(scanner.nextLine());
        break;
      case 2:
        result = product.setMaxAge(checkIntInput(scanner,"product name"));
        break;
      case 3:
        result = product.setMaxNumberEvent(checkIntInput(scanner, "max number event"));
        break;
      case 4:
        result = product.setPremium(checkIntInput(scanner,"premium"));
        break;
      case 5:
        result = product.setReductionPeriod(checkIntInput(scanner,"reduction period"));
        break;
      case 6:
        result = product.setReductionRatio(checkIntInput(scanner,"reduction ratio"));
        break;
      case 7:
        result = product.setSex(checkSexInput(scanner));
        break;
      case 8:
        result = product.setExemptionPeriod(checkIntInput(scanner,"exemption period"));
        break;
      case 9:
        result = product.setCoverageByAge(checkHashMap(scanner));
        break;
    }

    if (result)
      System.out.println("성공적으로 수정되었습니다.");
    else
      System.out.println("문제가 발생하였습니다.");
  }

  /**
   * 상품 등록 메뉴
   * @param insuranceProductList
   * @param scanner
   */
  public static void createMenu(InsuranceProductList insuranceProductList,java.util.Scanner scanner){
    System.out.println("product name : ");
    String productName = scanner.nextLine();
    while(true){
      if(productName.equals("")){
        System.out.println("다시 한번 입력해주세요.");
        productName = scanner.nextLine();
      }else
        break;
    }

    int maxAge = checkIntInput(scanner,"max age");
    int maxNumberEvent = checkIntInput(scanner,"max number event");
    int premium = checkIntInput(scanner,"premium");
    int reductionPeriod = checkIntInput(scanner,"reduction period");
    int reductionRatio = checkIntInput(scanner,"reduction ratio");
    Sex sex = checkSexInput(scanner);
    int exemptionPeriod = checkIntInput(scanner,"exemption period");
    HashMap<String,String> coverageByAge = checkHashMap(scanner);

    ProductManagement manager = new ProductManagement();
    if(manager.createProduct(insuranceProductList,coverageByAge,exemptionPeriod,reductionPeriod,reductionRatio,productName,sex,premium,maxAge,maxNumberEvent))
      System.out.println("상품이 정상적으로 등록되었습니다.");
    else // 같은 이름의 상품이 있는 경우 예외 처리
      System.out.println("같은 이름의 상품이 있어 드옥이 실해했습니다.");
  }

  /**
   * string입력 값을 HashMap<String,String>으로 변환
   * @param scanner
   * @return coverageByAge값을 HashMap<String,String>으로 반환
   */
  public static HashMap<String,String> checkHashMap(java.util.Scanner scanner){
    HashMap<String,String> hash = new HashMap<>();
    while(true){
      System.out.println("coverage by age : ");
      String coverageByAgStrings = scanner.nextLine();
      String[] array = coverageByAgStrings.split(" ");
      if(array.length %2 !=0){
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        continue;
      }
      for(int i=0;i<array.length-1;i+=2)
        hash.put(array[i],array[i+1]);
      break;
    }
    return hash;
  }

  /**
   * 입력값이 int 타입인지 확인 후 아니라면 재입력 받기
   * @param scanner
   * @param value
   * @return 입력값이 int 타입이라면 해당 값 반환
   */
  public static int checkIntInput(java.util.Scanner scanner, String value){
    int input = 0;
    while(true){
      System.out.println(value+" : ");
      if(scanner.hasNextInt()){
        input = scanner.nextInt();
        scanner.nextLine();
        break;
      }
      else{
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        scanner.nextLine();
      }
    }
    return input;
  }

  /**
   * 입력값에 따라 Sex 결정
   * @param scanner
   * @return 입력값에 따른 Sex 반환
   */
  public static Sex checkSexInput(java.util.Scanner scanner){
    Sex sex = null;
    String value = "";
    while(true){
      System.out.println("sex : ");
      value = scanner.nextLine();
      if(value.equals("m")||value.equals("M")||value.equals("Male")||value.equals("male")){
        sex = Sex.MALE;
        break;
      }
      else if(value.equals("f")||value.equals("F")||value.equals("Female")||value.equals("female")){
        sex = Sex.FEMALE;
        break;
      }
      System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }
    return sex;
  }
}
