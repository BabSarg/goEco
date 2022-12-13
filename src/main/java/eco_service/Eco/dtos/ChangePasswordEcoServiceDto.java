package eco_service.Eco.dtos;

import java.util.Objects;

public class ChangePasswordEcoServiceDto {
    private  String oldPassword;
    private  String newPassword;
    private  String confirmPassword;

    public ChangePasswordEcoServiceDto() {
    }

    public ChangePasswordEcoServiceDto(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangePasswordEcoServiceDto that = (ChangePasswordEcoServiceDto)  o;
        return Objects.equals(oldPassword, that.oldPassword) && Objects.equals(newPassword, that.newPassword) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldPassword, newPassword, confirmPassword);
    }
}
