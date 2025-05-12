/**
 * 怨�媛� ��蹂대�� �����대�� �대���ㅼ������. 鍮��� �⑦�댁�� �ъ�⑺���� 媛�泥대�� ���깊�⑸����.
 */
public class Customer {

	// --- ���� (紐⑤�� private, final濡� ���명���� 遺�蹂� 媛�泥대� 留���) ---
	private final String accountNumber;
	private final String address; // String �����쇰� 蹂�寃�
	private final int age;
	private final String customerID;
	private final String job;
	private final String name;
	private final String phoneNumber; // �대� 蹂�寃�
	private final String rrn;
	private final Sex sex; // Sex enum ���� �ъ��

	// --- ���깆�� (private�쇰� ���명���� �몃����� 吏��� �몄� 諛⑹�) ---
	private Customer(Builder builder) {
		this.accountNumber = builder.accountNumber;
		this.address = builder.address;
		this.age = builder.age;
		this.customerID = RandomIdGenerater.Generate();
		this.job = builder.job;
		this.name = builder.name;
		this.phoneNumber = builder.phoneNumber;
		this.rrn = builder.rrn;
		this.sex = builder.sex;
	}

	// --- Getter 硫������� ---
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		return age;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getJob() {
		return job;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRrn() {
		return rrn;
	}

	public Sex getSex() {
		return sex;
	}

	// --- toString 硫����� (���� 蹂�寃쎌�ы�� 諛���) ---
	@Override
	public String toString() {
		return "Customer{" +
				"accountNumber='" + accountNumber + '\'' +
				", address='" + address + '\'' + // String�쇰� 蹂�寃쎈��
				", age=" + age +
				", customerID='" + customerID + '\'' +
				", job='" + job + '\'' +
				", name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' + // �대� 蹂�寃쎈��
				", rrn='" + rrn + '\'' +
				", sex=" + sex + // Sex enum ����
				'}';
	}

	// --- 鍮��� ���� 硫����� ---
	public static Builder builder() {
		return new Builder();
	}

	// --- ���� �대� 鍮��� �대���� ---
	public static class Builder {
		// Customer ������ ���쇳�� ����瑜� 媛�吏� (final ����)
		private String accountNumber;
		private String address;
		private int age;
		private String job;
		private String name;
		private String phoneNumber;
		private String rrn;
		private Sex sex;

		// ���� ����媛� ���ㅻ㈃ 鍮��� ���깆������ 諛���濡� ������ �� ����
		// public Builder(String customerID, String name) {
		//     this.customerID = customerID;
		//     this.name = name;
		// }

		// 媛� ����瑜� �ㅼ������ 硫����� (硫����� 泥댁�대���� ���� Builder 諛���)
		public Builder accountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder job(String job) {
			this.job = job;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder rrn(String rrn) {
			this.rrn = rrn;
			return this;
		}

		public Builder sex(Sex sex) {
			this.sex = sex;
			return this;
		}

		// 理�醫����쇰� Customer 媛�泥대�� ���깊���� 硫�����
		public Customer build() {
			// �ш린�� ���� 媛� 寃�利� �깆�� 異�媛��� �� ����
			// if (name == null || customerID == null) {
			//     throw new IllegalStateException("Name and CustomerID cannot be null");
			// }
			return new Customer(this);
		}
	}
}
