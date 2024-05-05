// console.log("Hi");
// htmx.defineExtension("client-side-templates", {
//   transformResponse: function (text, xhr, elt) {
//     var nunjucksTemplate = htmx.closest(elt, "[nunjucks-template]");
//     if (nunjucksTemplate) {
//       console.log({ text });
//       var data = {
//         authors: JSON.parse(text),
//       };

//       var templateName = nunjucksTemplate.getAttribute("nunjucks-template");
//       var template = htmx.find("#" + templateName);
//       console.log(templateName, data);
//       return nunjucks.renderString(template.innerHTML, data);
//     }
//     return text;
//   },
// });
