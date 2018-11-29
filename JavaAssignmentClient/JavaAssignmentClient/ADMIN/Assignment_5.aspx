<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Assignment_5.aspx.cs" Inherits="JavaAssignmentClient.ADMIN.Assignment_5" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <h1>Assignment 5</h1>
            <div class="col-md-6">
                <asp:LinkButton ID="randomNumber" runat="server" OnClick="randomNumber_Click" >Gen RND Number</asp:LinkButton>
                <asp:Label  ID="randomNumberLAabel"  runat="server" />

                <asp:TextBox ID="amount" runat="server"></asp:TextBox>
                <asp:LinkButton  ID="genRndArray" runat="server" OnClick="genRndArray_Click" >Gen RND Array</asp:LinkButton>
                <asp:Label  AssociatedControlID="genRndArray" ID="rndArrayLabel"  runat="server" />
            </div>

            <div class="col-md-6">
                <asp:Button ID="getJobs" Text="Get Jobs" runat="server" OnClick="getJobs_Click" />
                <asp:GridView ID="JobsGridView" runat="server" >
                  
                </asp:GridView>


                <asp:TextBox ID="jobIdTestBox" runat="server">

                </asp:TextBox>
                <asp:Button ID="search" runat="server" Text="search for a job" OnClick="search_Click"/>

                <asp:Label ID="jobID" runat="server" Text="Label"></asp:Label>
                <asp:Label ID="jobTitle" runat="server" Text="Label"></asp:Label>
                <asp:Label ID="maxSalary" runat="server" Text="Label"></asp:Label>
                <asp:Label ID="minSalary" runat="server" Text="Label"></asp:Label>
            </div>

        </div>
    </form>
</body>
</html>
