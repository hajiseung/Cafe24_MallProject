package com.example.project_frontend.frontend.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.project_frontend.mall.MemberEnum;

public class UserVo {

	private long no;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "대,소문자 영어, 숫자만 입력해주세요")
	@Length(min = 4, max = 16, message = "4~16자 사이의 문자를 입력해주세요")
	private String id;

	@NotEmpty
	@Email(message = "잘못된 Email형식 입니다.")
	private String email;

	@NotEmpty
	@Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,20}", message = "비밀번호는 6자 이상 20자 이하의 알파벳, 숫자, 특수문자를 조합하여 작성해야 합니다.")
	@Length(min = 6, max = 20, message = "비밀번호 4자 이상 20자 이하")
	private String pw;

	@NotEmpty
	@Length(min = 2, max = 5)
	private String name;

	private String nickname;
	private String tell_ph;

	@NotEmpty
	@Pattern(regexp = "(010|011|016|017|018|019)-(\\d{3,4})-(\\d{4})", message = "잘못된 전화번호 형식입니다.")
	private String cell_ph;
	private String join_date;

	@NotEmpty
	private String addr;
	private int point;
	private int saving; // 적립금
	private String birthday;
	private int massive_mount; // 누적금액
	private String grade;
	private MemberEnum grade2;
	private boolean email_recv;
	private boolean sms_recv;
	private int purchase_cnt;
	private boolean islogin;

	private String role = "ROLE_USER";

	public long getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public String getTell_ph() {
		return tell_ph;
	}

	public String getCell_ph() {
		return cell_ph;
	}

	public String getJoin_date() {
		return join_date;
	}

	public String getAddr() {
		return addr;
	}

	public int getPoint() {
		return point;
	}

	public int getSaving() {
		return saving;
	}

	public String getBirthday() {
		return birthday;
	}

	public int getMassive_mount() {
		return massive_mount;
	}

	public String getGrade() {
		return grade;
	}

	public MemberEnum getGrade2() {
		return grade2;
	}

	public boolean isEmail_recv() {
		return email_recv;
	}

	public boolean isSms_recv() {
		return sms_recv;
	}

	public int getPurchase_cnt() {
		return purchase_cnt;
	}

	public boolean isIslogin() {
		return islogin;
	}

	public String getRole() {
		return role;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setTell_ph(String tell_ph) {
		this.tell_ph = tell_ph;
	}

	public void setCell_ph(String cell_ph) {
		this.cell_ph = cell_ph;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void setSaving(int saving) {
		this.saving = saving;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setMassive_mount(int massive_mount) {
		this.massive_mount = massive_mount;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setGrade2(MemberEnum grade2) {
		this.grade2 = grade2;
	}

	public void setEmail_recv(boolean email_recv) {
		this.email_recv = email_recv;
	}

	public void setSms_recv(boolean sms_recv) {
		this.sms_recv = sms_recv;
	}

	public void setPurchase_cnt(int purchase_cnt) {
		this.purchase_cnt = purchase_cnt;
	}

	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", email=" + email + ", pw=" + pw + ", name=" + name + ", nickname="
				+ nickname + ", tell_ph=" + tell_ph + ", cell_ph=" + cell_ph + ", join_date=" + join_date + ", addr="
				+ addr + ", point=" + point + ", saving=" + saving + ", birthday=" + birthday + ", massive_mount="
				+ massive_mount + ", grade=" + grade + ", grade2=" + grade2 + ", email_recv=" + email_recv
				+ ", sms_recv=" + sms_recv + ", purchase_cnt=" + purchase_cnt + ", islogin=" + islogin + ", role="
				+ role + "]";
	}

}
