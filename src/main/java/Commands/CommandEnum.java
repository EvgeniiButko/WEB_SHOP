package Commands;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTERREDIRECT{
        {
            this.command = new RegisterRedirectingCommand();
        }
    },
    REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },
    ADD_CARD{
        {
            this.command = new AddUserCardCommand();
        }
    },
    CARDREDIRECT{
        {
            this.command = new CardRedirectingCommand();
        }
    },
    MYCARD{
        {
            this.command = new ShowingUserCardsCommand();
        }
    },
    ADDPRODUCT{
        {
            this.command = new ProductAddingCommand();
        }
    },
    PRODUCTREDIRECT{
        {
            this.command = new ProductRedirecting();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
