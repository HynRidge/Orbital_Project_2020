from django.contrib import admin
from restaccount.models import RegisterUser
from django import forms
from django.contrib.auth.models import Group
from django.contrib.auth.admin import UserAdmin as BaseUserAdmin


class UserCreationForm(forms.ModelForm):
    password1 = forms.CharField(label = 'Password',widget = forms.PasswordInput)
    password2 = forms.CharField(label  = 'Password Confirmation', widget=forms.PasswordInput)

    class Meta:
        model =  RegisterUser   
        fields = ('phone_number',)
        
    def clean_password2(self):
        password1 = self.cleaned_data.get('password1')
        password2 = self.cleaned_data.get('password2')

        if password1 and password2 and password1 != password2:
            raise forms.ValidationError("Passwords didn't match")
        return password2

    def save(self,commit=True):
        user = super().save(commit=False)
        user.set_password(self.cleaned_data['password1'])
        if commit:
            user.save()
        return user


class UserChangeForm(forms.ModelForm):
    class Meta:
        model = RegisterUser
        fields = ('phone_number', 'password','is_active','is_admin')

    def clean_password(self):
        return self.initial["password"]



class UserAdmin(BaseUserAdmin):
    form = UserChangeForm
    add_form = UserCreationForm

    list_display = ('phone_number','is_admin')
    list_filter = ('is_admin',)
    fieldsets = (
        (None,{'fields' : ('email', 'password')}),
        ('Personal info',{'fields' : ('phone_number',)}),
        ('Permissions',{'fields' : ('is_admin',)}),
        )

    add_fieldsets = (
        (None, {
            'classes' : ('wide'),
            'fields' : ('phone_number','password1','password2'),
        }),
    )

    search_fields = ('phone_number',)
    ordering = ('email',)
    filter_horizontal=()

admin.site.register(RegisterUser,UserAdmin)
admin.site.unregister(Group)
# admin.site.register(Login)

# Register your models here.
