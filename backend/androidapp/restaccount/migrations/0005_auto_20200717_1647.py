# Generated by Django 3.0.7 on 2020-07-17 08:47

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('restaccount', '0004_contact'),
    ]

    operations = [
        migrations.AddField(
            model_name='registeruser',
            name='profile_image',
            field=models.ImageField(default='media/download.jpg', upload_to='media'),
        ),
        migrations.AlterField(
            model_name='registeruser',
            name='email',
            field=models.EmailField(default='', max_length=255),
        ),
        migrations.AlterField(
            model_name='registeruser',
            name='first_name',
            field=models.CharField(default='', max_length=255),
        ),
        migrations.AlterField(
            model_name='registeruser',
            name='last_name',
            field=models.CharField(default='', max_length=255),
        ),
        migrations.AlterField(
            model_name='registeruser',
            name='nickname',
            field=models.CharField(default='', max_length=255),
        ),
    ]